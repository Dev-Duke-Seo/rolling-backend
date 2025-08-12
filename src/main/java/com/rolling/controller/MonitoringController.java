package com.rolling.controller;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MonitoringController {
    private final EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager entityManager;

    // 애플리케이션 시작 시 통계 기능 활성화
    @PostConstruct
    public void enableStatistics() {
        try {
            SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
            sessionFactory.getStatistics().setStatisticsEnabled(true);
            System.out.println("Hibernate 통계 기능 활성화됨");
        } catch (Exception e) {
            System.err.println("Hibernate 통계 기능 활성화 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @GetMapping("/query-stats")
    public Map<String, Object> getQueryStats() {
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        Statistics stats = sessionFactory.getStatistics();

        // 통계 기능이 활성화되어 있는지 확인
        if (!stats.isStatisticsEnabled()) {
            stats.setStatisticsEnabled(true);
            System.out.println("통계 기능 활성화됨");
        }

        // 통계 수집 전 EntityManager 플러시로 보류 중인 변경사항 모두 적용
        try {
            entityManager.flush();
            System.out.println("EntityManager 플러시 수행됨");
        } catch (Exception e) {
            System.err.println("EntityManager 플러시 실패: " + e.getMessage());
        }

        Map<String, Object> result = new HashMap<>();

        // 기본 쿼리 통계
        result.put("queryCount", stats.getQueryExecutionCount());
        result.put("maxTime", stats.getQueryExecutionMaxTime());
        result.put("maxTimeQueryString", stats.getQueryExecutionMaxTimeQueryString());
        result.put("statisticsEnabled", stats.isStatisticsEnabled());

        // SQL 쿼리 분석을 통한 작업 유형 카운트
        Map<String, Integer> sqlOperationCounts = analyzeSqlQueries(stats.getQueries());
        result.put("sqlOperations", sqlOperationCounts);

        // 엔티티 통계 수집
        Map<String, Map<String, Object>> entityStats = new HashMap<>();
        for (String entity : stats.getEntityNames()) {
            Map<String, Object> counts = new HashMap<>();

            // 기본 CRUD 통계
            counts.put("fetch", stats.getEntityStatistics(entity).getFetchCount());
            counts.put("insert", stats.getEntityStatistics(entity).getInsertCount());
            counts.put("update", stats.getEntityStatistics(entity).getUpdateCount());
            counts.put("delete", stats.getEntityStatistics(entity).getDeleteCount());
            counts.put("loadCount", stats.getEntityStatistics(entity).getLoadCount());
            counts.put("optimisticFailureCount",
                    stats.getEntityStatistics(entity).getOptimisticFailureCount());

            // 엔티티 이름에서 테이블 이름 추출
            String simpleEntityName = entity.substring(entity.lastIndexOf('.') + 1);
            String tableName = simpleEntityName.toLowerCase() + "s"; // 단순 복수형 가정

            // SQL 분석을 통한 추정 작업 카운트 추가
            Map<String, Integer> entitySqlCounts =
                    countEntityOperations(stats.getQueries(), simpleEntityName, tableName);
            counts.put("estimatedOperations", entitySqlCounts);

            entityStats.put(entity, counts);

            // 디버깅 로그
            System.out.println("엔티티 통계 - " + entity + ": " + "fetch="
                    + stats.getEntityStatistics(entity).getFetchCount() + ", load="
                    + stats.getEntityStatistics(entity).getLoadCount() + ", insert="
                    + stats.getEntityStatistics(entity).getInsertCount() + ", update="
                    + stats.getEntityStatistics(entity).getUpdateCount() + ", delete="
                    + stats.getEntityStatistics(entity).getDeleteCount());

            // 추정 통계도 로깅
            System.out.println("추정 통계 - " + entity + ": " + entitySqlCounts);
        }
        result.put("entityStats", entityStats);

        // n+1 문제 검출 로직
        Map<String, Object> n1Detection = new HashMap<>();
        Map<String, Long> queryCountByEntity = new HashMap<>();

        // 패턴 분석: 동일 엔티티에 대한 반복 쿼리 패턴 확인
        String[] queries = stats.getQueries();

        // 디버깅을 위한 로깅
        System.out.println("통계 활성화 상태: " + stats.isStatisticsEnabled());
        System.out.println("쿼리 개수: " + (queries != null ? queries.length : 0));

        if (queries != null && queries.length > 0) {
            System.out.println("쿼리 예시 (최대 3개):");
            for (int i = 0; i < Math.min(3, queries.length); i++) {
                System.out.println("쿼리 #" + (i + 1) + ": " + queries[i]);
            }
        } else {
            System.out.println("저장된 쿼리가 없습니다.");
        }

        System.out.println("엔티티 이름: " + Arrays.toString(stats.getEntityNames()));

        // 엔티티별 쿼리 카운트
        for (String query : queries) {
            // 엔티티명 추출 (간단한 휴리스틱)
            for (String entityFullName : stats.getEntityNames()) {
                String entitySimpleName =
                        entityFullName.substring(entityFullName.lastIndexOf('.') + 1);
                String tableName = entitySimpleName.toLowerCase() + "s"; // 단순 복수형 테이블명 가정

                // HQL/JPQL에서의 엔티티명 검사
                boolean matched = query.contains(" " + entitySimpleName + " ")
                        || query.contains("FROM " + entitySimpleName + " ")
                        || query.contains("JOIN " + entitySimpleName + " ")
                        || query.contains(" " + entitySimpleName + ".")
                        || query.contains("SELECT " + entitySimpleName + " ") ||

                        // SQL에서의 테이블명 검사
                        query.contains(" " + tableName + " ")
                        || query.contains("FROM " + tableName + " ")
                        || query.contains("JOIN " + tableName + " ")
                        || query.contains(" " + tableName + ".") ||

                        // 별칭 검사 (간단한 휴리스틱)
                        query.contains("FROM " + entitySimpleName + " ")
                        || query.contains("FROM " + tableName + " ")
                        || query.contains(".*" + entitySimpleName + ".*");

                if (matched) {
                    System.out.println("매치된 엔티티: " + entityFullName + " (쿼리: " + query + ")");
                    queryCountByEntity.compute(entityFullName, (k, v) -> v == null ? 1L : v + 1);
                }
            }
        }

        // 추가: Collection 로딩 통계
        for (String roleStr : stats.getCollectionRoleNames()) {
            // collection role은 보통 "com.example.Entity.collectionField" 형태
            String entityName = roleStr.substring(0, roleStr.lastIndexOf('.'));
            long loadCount = stats.getCollectionStatistics(roleStr).getLoadCount();
            System.out.println("컬렉션 로드: " + roleStr + " 로드 횟수: " + loadCount);

            if (loadCount > 0) {
                queryCountByEntity.compute(entityName,
                        (k, v) -> v == null ? loadCount : v + loadCount);
            }
        }

        // N+1 가능성 높은 엔티티 식별 (임계값: 10회 이상 동일 엔티티 쿼리)
        Map<String, Object> suspiciousEntities = new HashMap<>();
        queryCountByEntity.forEach((entity, count) -> {
            if (count > 10) {
                suspiciousEntities.put(entity, count);
            }
        });

        n1Detection.put("suspiciousEntities", suspiciousEntities);
        n1Detection.put("queryPatterns", queryCountByEntity);
        result.put("n1Detection", n1Detection);

        return result;
    }

    /**
     * SQL 쿼리를 분석하여 작업 유형별 카운트 반환
     */
    private Map<String, Integer> analyzeSqlQueries(String[] queries) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("select", 0);
        counts.put("insert", 0);
        counts.put("update", 0);
        counts.put("delete", 0);

        if (queries == null)
            return counts;

        for (String query : queries) {
            query = query.trim().toLowerCase();

            if (query.startsWith("select")) {
                counts.put("select", counts.get("select") + 1);
            } else if (query.startsWith("insert")) {
                counts.put("insert", counts.get("insert") + 1);
            } else if (query.startsWith("update")) {
                counts.put("update", counts.get("update") + 1);
            } else if (query.startsWith("delete")) {
                counts.put("delete", counts.get("delete") + 1);
            }
        }

        return counts;
    }

    /**
     * 특정 엔티티와 관련된 SQL 작업 카운트 추정
     */
    private Map<String, Integer> countEntityOperations(String[] queries, String entityName,
            String tableName) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("select", 0);
        counts.put("insert", 0);
        counts.put("update", 0);
        counts.put("delete", 0);

        if (queries == null)
            return counts;

        for (String query : queries) {
            query = query.trim().toLowerCase();
            boolean matchesEntity = query.contains(entityName.toLowerCase())
                    || query.contains(tableName.toLowerCase());

            if (!matchesEntity)
                continue;

            if (query.startsWith("select")) {
                counts.put("select", counts.get("select") + 1);
            } else if (query.startsWith("insert")) {
                counts.put("insert", counts.get("insert") + 1);
            } else if (query.startsWith("update")) {
                counts.put("update", counts.get("update") + 1);
            } else if (query.startsWith("delete")) {
                counts.put("delete", counts.get("delete") + 1);
            }
        }

        return counts;
    }

    @PostMapping("/reset-stats")
    public void resetStats() {
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        sessionFactory.getStatistics().clear();
        System.out.println("통계가 초기화되었습니다.");
    }
}
