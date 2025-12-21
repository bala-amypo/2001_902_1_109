package com.example.demo.Repository;

import com.example.demo.Entity.CreditCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRecordRepository
        extends JpaRepository<CreditCardRecord, Long> {

    List<CreditCardRecord> findByUserId(Long userId);
}



















