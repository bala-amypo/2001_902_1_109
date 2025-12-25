package com.example.demo.controller;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendation", description = "Recommendation Engine API")
public class RecommendationController {
    
    @Autowired
    private RecommendationEngineService recommendationService;
    
    @GetMapping
    @Operation(summary = "Get all recommendations")
    public ResponseEntity<List<RecommendationRecord>> getAllRecommendations() {
        return ResponseEntity.ok(recommendationService.getAllRecommendations());
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get recommendations by user ID")
    public ResponseEntity<List<RecommendationRecord>> getRecommendationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByUser(userId));
    }
    
    @PostMapping("/generate/{intentId}")
    @Operation(summary = "Generate recommendation for purchase intent")
    public ResponseEntity<RecommendationRecord> generateRecommendation(@PathVariable Long intentId) {
        return ResponseEntity.ok(recommendationService.generateRecommendation(intentId));
    }
}