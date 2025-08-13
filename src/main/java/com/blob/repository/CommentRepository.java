package com.blob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blob.entity.BlobUser;
import com.blob.entity.Comment;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.isDeleted = false AND c.commentId = :commentId")
    Optional<Comment> findActiveCommentById(@Param("commentId") Long commentId);

    @Query("SELECT c FROM Comment c WHERE c.isDeleted = false AND c.post.postId = :postId AND c.parentComment IS NULL ORDER BY c.createdAt ASC")
    Page<Comment> findActiveCommentsByPostId(@Param("postId") Long postId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.isDeleted = false AND c.parentComment.commentId = :parentCommentId ORDER BY c.createdAt ASC")
    Page<Comment> findActiveRepliesByParentId(@Param("parentCommentId") Long parentCommentId,
            Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.isDeleted = false AND c.user.userId = :userId ORDER BY c.createdAt DESC")
    Page<Comment> findActiveCommentsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.isDeleted = false AND c.post.postId = :postId")
    Long countActiveCommentsByPostId(@Param("postId") Long postId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.isDeleted = false AND c.parentComment.commentId = :parentCommentId")
    Long countActiveRepliesByParentId(@Param("parentCommentId") Long parentCommentId);

    Page<Comment> findByUser(BlobUser user, Pageable pageable);

    Page<Comment> findByParentCommentCommentIdAndIsDeletedFalse(Long commentId, Pageable pageable);

    Page<Comment> findByPostPostIdAndParentCommentIsNullAndIsDeletedFalse(Long postId,
            Pageable pageable);
}
