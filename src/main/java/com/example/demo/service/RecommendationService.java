package com.example.demo.service;

import java.util.List;

public interface RecommendationService {

    List<String> generateRecommendation(Long userId);
}
