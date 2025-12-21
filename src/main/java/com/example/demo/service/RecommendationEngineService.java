package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationEngineService {

    private final PurchaseIntentRecordRepository intentRepository;
    private final CreditCardRecordRepository cardRepository;
    private final RewardRuleRepository ruleRepository;
    private final RecommendationRecordRepository recommendationRepository;

    public RecommendationEngineService(
            PurchaseIntentRecordRepository intentRepository,
            CreditCardRecordRepository cardRepository,
            RewardRuleRepository ruleRepository,
            RecommendationRecordRepository recommendationRepository) {

        this.intentRepository = intentRepository;
        this.cardRepository = cardRepository;
        this.ruleRepository = ruleRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public RecommendationRecord generateRecommendation(Long intentId) {

        PurchaseIntentRecord intent =
                intentRepository.findById(intentId).orElse(null);

        if (intent == null) {
            return null;
        }

        List<CreditCardRecord> cards =
                cardRepository.findByUserId(intent.getUserId());

        double maxReward = 0;
        Long bestCardId = null;

        for (CreditCardRecord card : cards) {
            List<RewardRule> rules =
                    ruleRepository.findActiveRulesForCardCategory(
                            card.getId(),
                            intent.getCategory()
                    );

            for (RewardRule rule : rules) {
                double reward = intent.getAmount() * rule.getMultiplier();
                if (reward > maxReward) {
                    maxReward = reward;
                    bestCardId = card.getId();
                }
            }
        }

        RecommendationRecord recommendation = new RecommendationRecord();
        recommendation.setUserId(intent.getUserId());
        recommendation.setPurchaseIntentId(intent.getId());
        recommendation.setRecommendedCardId(bestCardId);
        recommendation.setExpectedRewardValue(maxReward);
        recommendation.setCalculationDetailsJson("Calculated using reward rules");

        return recommendationRepository.save(recommendation);
    }

    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }
}
