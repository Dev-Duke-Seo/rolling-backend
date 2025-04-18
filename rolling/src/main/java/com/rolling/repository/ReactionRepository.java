package com.rolling.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rolling.entity.Reaction;

import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Page<Reaction> findByRecipientId(Long recipientId, Pageable pageable);

    Optional<Reaction> findByRecipientIdAndEmoji(Long recipientId, String emoji);
}
