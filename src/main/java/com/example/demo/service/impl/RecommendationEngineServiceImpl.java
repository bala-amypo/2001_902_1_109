package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository intentRepository;
    private final CreditCardRecordRepository cardRepository;
    private final RewardRuleRepository ruleRepository;
    private final RecommendationRecordRepository recommendationRepository;

    public RecommendationServiceImpl(
            PurchaseIntentRecordRepository intentRepository,
            CreditCardRecordRepository cardRepository,
            RewardRuleRepository ruleRepository,
            RecommendationRecordRepository recommendationRepository) {

        this.intentRepository = intentRepository;
        this.cardRepository = cardRepository;
        this.ruleRepository = ruleRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
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

        RecommendationRecord record = new RecommendationRecord();
        record.setUserId(intent.getUserId());
        record.setPurchaseIntentId(intent.getId());
        record.setRecommendedCardId(bestCardId);
        record.setExpectedRewardValue(maxReward);
        record.setCalculationDetailsJson("Reward calculated");

        return recommendationRepository.save(record);
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }
}