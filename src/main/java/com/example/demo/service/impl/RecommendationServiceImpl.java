package com.example.demo.service.impl;

import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<String> generateRecommendation(Long userId) {
        // Dummy logic (replace later)
        List<String> recommendations = new ArrayList<>();
        recommendations.add("Platinum Credit Card");
        recommendations.add("Cashback Credit Card");
        return recommendations;
    }
}
