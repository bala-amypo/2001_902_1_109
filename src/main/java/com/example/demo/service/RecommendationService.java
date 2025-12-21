package com.example.demo.service;

import java.util.List;

public interface RecommendationService {

    /**
     * Generate card recommendations for a user
     * @param userId user id
     * @return list of recommended card names (or ids)
     */
    List<String> generateRecommendation(Long userId);
}
