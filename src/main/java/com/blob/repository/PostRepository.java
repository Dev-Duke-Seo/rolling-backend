package com.blob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.blob.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND p.postId = :postId")
       Optional<Post> findActivePostById(@Param("postId") Long postId);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false ORDER BY p.createdAt DESC")
       Page<Post> findAllActivePosts(Pageable pageable);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND p.user.userId = :userId ORDER BY p.createdAt DESC")
       Page<Post> findActivePostsByUserId(@Param("userId") Long userId, Pageable pageable);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND p.category = :category ORDER BY p.createdAt DESC")
       Page<Post> findActivePostsByCategory(@Param("category") String category, Pageable pageable);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND "
                     + "(p.title LIKE %:keyword% OR p.content LIKE %:keyword%) ORDER BY p.createdAt DESC")
       Page<Post> findActivePostsByKeyword(@Param("keyword") String keyword, Pageable pageable);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND "
                     + "p.latitude BETWEEN :minLat AND :maxLat AND "
                     + "p.longitude BETWEEN :minLng AND :maxLng")
       List<Post> findActivePostsInArea(@Param("minLat") Double minLat,
                     @Param("maxLat") Double maxLat, @Param("minLng") Double minLng,
                     @Param("maxLng") Double maxLng);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND p.likeCount >= :minLikes ORDER BY p.likeCount DESC")
       Page<Post> findActivePostsByMinLikes(@Param("minLikes") Integer minLikes, Pageable pageable);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND "
                     + "p.createdAt BETWEEN :startDate AND :endDate ORDER BY p.createdAt DESC")
       Page<Post> findActivePostsByDateRange(@Param("startDate") LocalDateTime startDate,
                     @Param("endDate") LocalDateTime endDate, Pageable pageable);

       @Query("SELECT p FROM Post p JOIN p.images i WHERE p.isDeleted = false GROUP BY p ORDER BY p.createdAt DESC")
       Page<Post> findActivePostsWithImages(Pageable pageable);

       @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND "
                     + "p.latitude IS NOT NULL AND p.longitude IS NOT NULL ORDER BY p.createdAt DESC")
       Page<Post> findActivePostsWithLocation(Pageable pageable);
}
