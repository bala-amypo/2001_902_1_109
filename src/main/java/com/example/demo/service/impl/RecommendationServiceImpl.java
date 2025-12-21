package com.example.demo.repository;

import com.example.demo.entity.CreditCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository
        extends JpaRepository<CreditCardRecord, Long> {

    List<CreditCardRecord> findByUserId(Long userId);
}
