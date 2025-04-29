package com.rolling.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rolling.model.entity.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    Optional<Recipient> findById(Long id);

    Optional<Recipient> findFirstByName(String name);

    // 생성일 기준 정렬된 메소드 추가
    List<Recipient> findAllByOrderByCreatedAtDesc();

    // 페이징과 함께 사용하는 경우
    Page<Recipient> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 메시지 개수 기준 내림차순 정렬
    @Query("SELECT r FROM Recipient r LEFT JOIN r.messages m GROUP BY r ORDER BY COUNT(m) DESC")
    Page<Recipient> findAllByOrderByMessageCountDesc(Pageable pageable);
}
