package com.blob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.blob.entity.BlobUser;
import java.util.Optional;

@Repository
public interface BlobUserRepository extends JpaRepository<BlobUser, Long> {

    Optional<BlobUser> findByBlobId(String blobId);

    Optional<BlobUser> findByEmail(String email);

    Optional<BlobUser> findByProviderAndProviderId(String provider, String providerId);

    boolean existsByBlobId(String blobId);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM BlobUser u WHERE u.isDeleted = false AND u.userId = :userId")
    Optional<BlobUser> findActiveUserById(Long userId);

    @Query("SELECT u FROM BlobUser u WHERE u.isDeleted = false AND u.blobId = :blobId")
    Optional<BlobUser> findActiveUserByBlobId(String blobId);
}
