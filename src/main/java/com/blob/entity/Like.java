package com.blob.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "blob_likes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "target_type", "target_id"})},
        indexes = {@Index(name = "idx_blob_like_user", columnList = "user_id"),
                @Index(name = "idx_blob_like_target", columnList = "target_type,target_id")})
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private BlobUser user;

    @Column(name = "target_type", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private LikeTargetType targetType;

    @Column(name = "target_id", nullable = false)
    private Long targetId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum LikeTargetType {
        POST, COMMENT
    }
}
