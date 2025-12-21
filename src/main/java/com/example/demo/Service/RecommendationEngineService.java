package com.example.demo.Service;

import com.example.demo.Entity.RecommendationRecord;

import java.util.List;

public interface RecommendationService {
    RecommendationRecord generateRecommendation(Long intentId);
    List<RecommendationRecord> getRecommendationsByUser(Long userId);
}