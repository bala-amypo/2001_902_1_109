package com.example.demo.controller;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {
    private final RecommendationEngineService recommendationEngineService;

    public RecommendationController(RecommendationEngineService recommendationEngineService) {
        this.recommendationEngineService = recommendationEngineService;
    }

    @PostMapping("/generate/{intentId}")
    public RecommendationRecord generateRecommendation(@PathVariable Long intentId) {
        return recommendationEngineService.generateRecommendation(intentId);
    }

    @GetMapping("/user/{userId}")
    public List<RecommendationRecord> getRecommendationsByUser(@PathVariable Long userId) {
        return recommendationEngineService.getRecommendationsByUser(userId);
    }

    @GetMapping
    public List<RecommendationRecord> getAllRecommendations() {
        return recommendationEngineService.getAllRecommendations();
    }
}