package com.blob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blob.entity.Like;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

        Optional<Like> findByUserUserIdAndTargetTypeAndTargetId(Long userId,
                        Like.LikeTargetType targetType, Long targetId);

        boolean existsByUserUserIdAndTargetTypeAndTargetId(Long userId,
                        Like.LikeTargetType targetType, Long targetId);

        @Query("SELECT COUNT(l) FROM Like l WHERE l.targetType = :targetType AND l.targetId = :targetId")
        Long countByTargetTypeAndTargetId(@Param("targetType") Like.LikeTargetType targetType,
                        @Param("targetId") Long targetId);

        void deleteByUserUserIdAndTargetTypeAndTargetId(Long userId, Like.LikeTargetType targetType,
                        Long targetId);
}
