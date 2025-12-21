package com.example.demo.Service;

import com.example.demo.entity.RecommendationRecord;
import java.util.List;

public interface RecommendationEngineService {

    RecommendationRecord saveRecommendation(RecommendationRecord recommendation);

    RecommendationRecord getRecommendationById(Long id);

    List<RecommendationRecord> getRecommendationsByUser(Long userId);

    List<RecommendationRecord> getAllRecommendations();
}
