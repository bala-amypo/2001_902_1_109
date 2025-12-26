package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationEngineServiceImpl implements RecommendationEngineService {
    private final PurchaseIntentRecordRepository purchaseIntentRepository;
    private final UserProfileRepository userProfileRepository;
    private final CreditCardRecordRepository creditCardRepository;
    private final RewardRuleRepository rewardRuleRepository;
    private final RecommendationRecordRepository recommendationRecordRepository;

    public RecommendationEngineServiceImpl(
            PurchaseIntentRecordRepository purchaseIntentRepository,
            UserProfileRepository userProfileRepository,
            CreditCardRecordRepository creditCardRepository,
            RewardRuleRepository rewardRuleRepository,
            RecommendationRecordRepository recommendationRecordRepository) {
        this.purchaseIntentRepository = purchaseIntentRepository;
        this.userProfileRepository = userProfileRepository;
        this.creditCardRepository = creditCardRepository;
        this.rewardRuleRepository = rewardRuleRepository;
        this.recommendationRecordRepository = recommendationRecordRepository;
    }

    @Override
    public RecommendationRecord generateRecommendation(Long intentId) {
        Optional<PurchaseIntentRecord> intentOpt = purchaseIntentRepository.findById(intentId);
        if (!intentOpt.isPresent()) {
            throw new BadRequestException("Purchase intent not found");
        }
        
        PurchaseIntentRecord intent = intentOpt.get();
        Optional<UserProfile> userOpt = userProfileRepository.findById(intent.getUserId());
        if (!userOpt.isPresent()) {
            throw new BadRequestException("User not found");
        }

        List<CreditCardRecord> cards = creditCardRepository.findActiveCardsByUser(intent.getUserId());
        if (cards.isEmpty()) {
            throw new BadRequestException("No active cards found for user");
        }

        CreditCardRecord bestCard = cards.get(0);
        double bestReward = 0.0;

        for (CreditCardRecord card : cards) {
            List<RewardRule> rules = rewardRuleRepository.findActiveRulesForCardCategory(card.getId(), intent.getCategory());
            for (RewardRule rule : rules) {
                double reward = intent.getAmount() * rule.getMultiplier();
                if (reward > bestReward) {
                    bestReward = reward;
                    bestCard = card;
                }
            }
        }

        RecommendationRecord recommendation = new RecommendationRecord();
        recommendation.setUserId(intent.getUserId());
        recommendation.setPurchaseIntentId(intentId);
        recommendation.setRecommendedCardId(bestCard.getId());
        recommendation.setExpectedRewardValue(bestReward);
        recommendation.setCalculationDetailsJson("{\"calculation\":\"completed\"}");

        return recommendationRecordRepository.save(recommendation);
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRecordRepository.findByUserId(userId);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recommendationRecordRepository.findAll();
    }
}