package com.rolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rolling.model.entity.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}