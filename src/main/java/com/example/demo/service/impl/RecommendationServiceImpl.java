package com.example.demo.service.impl;

import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<String> generateRecommendation(Long userId) {
        return List.of("Platinum Card", "Cashback Card");
    }
}
