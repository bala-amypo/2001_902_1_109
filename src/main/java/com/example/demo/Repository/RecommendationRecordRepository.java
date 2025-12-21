package com.example.demo.Repository;

import com.example.demo.Entity.RecommendationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRecordRepository
        extends JpaRepository<RecommendationRecord, Long> {

    List<RecommendationRecord> findByUserId(Long userId);
}