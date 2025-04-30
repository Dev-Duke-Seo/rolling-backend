package com.rolling.controller;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MonitoringController {
    private final EntityManagerFactory emf;

    @GetMapping("/query-stats")
    public Map<String, Object> getQueryStats() {
        // 통계 기능이 기본적으로 비활성화 상태
        // 코드에서 명시적으로 활성화해야 함
        try {
            SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
            sessionFactory.getStatistics().setStatisticsEnabled(true);
        } catch (Exception e) {
            // 처리 로직
        }

        Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
        System.out.println("stats: " + stats);
        Map<String, Object> result = new HashMap<>();

        result.put("queryCount", stats.getQueryExecutionCount());
        result.put("maxTime", stats.getQueryExecutionMaxTime());
        result.put("maxTimeQueryString", stats.getQueryExecutionMaxTimeQueryString());

        Map<String, Map<String, Long>> entityStats = new HashMap<>();
        for (String entity : stats.getEntityNames()) {
            Map<String, Long> counts = new HashMap<>();
            counts.put("fetch", stats.getEntityStatistics(entity).getFetchCount());
            counts.put("insert", stats.getEntityStatistics(entity).getInsertCount());
            counts.put("update", stats.getEntityStatistics(entity).getUpdateCount());
            counts.put("delete", stats.getEntityStatistics(entity).getDeleteCount());
            entityStats.put(entity, counts);
        }
        result.put("entityStats", entityStats);

        // n+1 문제 검출 로직
        Map<String, Object> n1Detection = new HashMap<>();
        Map<String, Long> queryCountByEntity = new HashMap<>();

        // 패턴 분석: 동일 엔티티에 대한 반복 쿼리 패턴 확인
        String[] queries = stats.getQueries();
        for (String query : queries) {
            // 엔티티명 추출 (간단한 휴리스틱)
            for (String entity : stats.getEntityNames()) {
                if (query.contains(" " + entity + " ") ||
                        query.contains("FROM " + entity)) {
                    queryCountByEntity.compute(entity, (k, v) -> v == null ? 1L : v + 1);
                }
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

    @PostMapping("/reset-stats")
    public void resetStats() {
        emf.unwrap(SessionFactory.class).getStatistics().clear();
    }
}
