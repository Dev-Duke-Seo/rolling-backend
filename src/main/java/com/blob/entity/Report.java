package com.blob.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "blob_reports",
        indexes = {@Index(name = "idx_blob_report_reporter", columnList = "reporter_id"),
                @Index(name = "idx_blob_report_target", columnList = "target_type,target_id"),
                @Index(name = "idx_blob_report_status", columnList = "status")})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private BlobUser reporter;

    @Column(name = "target_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ReportTargetType targetType;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Column(nullable = false, length = 100)
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReportStatus status = ReportStatus.PENDING;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum ReportTargetType {
        POST, COMMENT
    }

    public enum ReportStatus {
        PENDING, REVIEWED, APPROVED, REJECTED
    }
}
