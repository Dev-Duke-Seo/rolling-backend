package com.blob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blob.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

        @Query("SELECT r FROM Report r WHERE r.status = :status ORDER BY r.createdAt DESC")
        Page<Report> findByStatus(@Param("status") Report.ReportStatus status, Pageable pageable);

        @Query("SELECT r FROM Report r WHERE r.targetType = :targetType AND r.targetId = :targetId ORDER BY r.createdAt DESC")
        Page<Report> findByTargetTypeAndTargetId(
                        @Param("targetType") Report.ReportTargetType targetType,
                        @Param("targetId") Long targetId, Pageable pageable);

        boolean existsByReporterUserIdAndTargetTypeAndTargetId(Long reporterId,
                        Report.ReportTargetType targetType, Long targetId);
}
