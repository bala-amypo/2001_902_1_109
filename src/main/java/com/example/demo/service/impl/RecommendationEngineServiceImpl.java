package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecommendationEngineServiceImpl implements RecommendationEngineService {
    
    @Autowired
    private PurchaseIntentRecordRepository intentRepository;
    
    @Autowired
    private UserProfileRepository userRepository;
    
    @Autowired
    private CreditCardRecordRepository cardRepository;
    
    @Autowired
    private RewardRuleRepository ruleRepository;
    
    @Autowired
    private RecommendationRecordRepository recommendationRepository;
    
    @Override
    public RecommendationRecord generateRecommendation(Long purchaseIntentId) {
        PurchaseIntentRecord intent = intentRepository.findById(purchaseIntentId).orElse(null);
        if (intent == null) return null;
        
        UserProfile user = userRepository.findById(intent.getUserId()).orElse(null);
        if (user == null) return null;
        
        List<CreditCardRecord> cards = cardRepository.findActiveCardsByUser(intent.getUserId());
        if (cards.isEmpty()) {
            throw new RuntimeException("No active cards found");
        }
        
        CreditCardRecord bestCard = cards.get(0);
        double bestReward = 0.0;
        
        for (CreditCardRecord card : cards) {
            List<RewardRule> rules = ruleRepository.findActiveRulesForCardCategory(card.getId(), intent.getCategory());
            for (RewardRule rule : rules) {
                double reward = intent.getAmount() * rule.getMultiplier();
                if (reward > bestReward) {
                    bestReward = reward;
                    bestCard = card;
                }
            }
        }
        
        RecommendationRecord rec = new RecommendationRecord();
        rec.setUserId(intent.getUserId());
        rec.setPurchaseIntentId(purchaseIntentId);
        rec.setRecommendedCardId(bestCard.getId());
        rec.setExpectedRewardValue(bestReward);
        
        return recommendationRepository.save(rec);
    }
    
    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }
    
    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recommendationRepository.findAll();
    }
}
