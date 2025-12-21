package com.example.demo.Service.Implement;

import com.example.demo.Entity.CreditCardRecord;
import com.example.demo.Repository.CreditCardRecordRepository;
import com.example.demo.Service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRecordRepository repository;

    public CreditCardServiceImpl(CreditCardRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        return repository.save(card);
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}






package com.example.demo.Service.Implement;

import com.example.demo.Entity.PurchaseIntentRecord;
import com.example.demo.Repository.PurchaseIntentRecordRepository;
import com.example.demo.Service.PurchaseIntentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository repository;

    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        return repository.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}





package com.example.demo.Service.Implement;

import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

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






package com.example.demo.Service.Implement;

import com.example.demo.Entity.RewardRule;
import com.example.demo.Repository.RewardRuleRepository;
import com.example.demo.Service.RewardRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository repository;

    public RewardRuleServiceImpl(RewardRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public RewardRule createRule(RewardRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<RewardRule> getAllRules() {
        return repository.findAll();
    }
}





