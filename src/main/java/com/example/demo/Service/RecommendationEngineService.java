package com.example.demo.Service.Implement;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.Repository.RecommendationRepository;
import com.example.demo.Service.RecommendationEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationEngineServiceImplement
        implements RecommendationEngineService {

    private final RecommendationRepository repository;

    // ✅ THIS LINE MUST BE EXACT
    public RecommendationEngineServiceImplement(
            RecommendationRepository repository) {
        this.repository = repository;
    }

    @Override
    public RecommendationRecord saveRecommendation(
            RecommendationRecord recommendation) {
        return repository.save(recommendation);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return repository.findAll();
    }
}
