package com.blob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blob.entity.Bookmark;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserUserIdAndPostPostId(Long userId, Long postId);

    boolean existsByUserUserIdAndPostPostId(Long userId, Long postId);

    @Query("SELECT b FROM Bookmark b WHERE b.user.userId = :userId ORDER BY b.createdAt DESC")
    Page<Bookmark> findByUserId(@Param("userId") Long userId, Pageable pageable);

    void deleteByUserUserIdAndPostPostId(Long userId, Long postId);
}
