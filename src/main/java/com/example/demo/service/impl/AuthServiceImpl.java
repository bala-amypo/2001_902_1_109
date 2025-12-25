package com.example.demo.service.impl;

import com.example.demo.service.AuthService;

public class AuthServiceImpl implements AuthService {
    // Authentication service implementation
}












package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    
    @Autowired
    private CreditCardRecordRepository repository;
    
    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        return repository.save(card);
    }
    
    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
    
    @Override
    public List<CreditCardRecord> getAllCards() {
        return repository.findAll();
    }
}











package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {
    
    @Autowired
    private PurchaseIntentRecordRepository repository;
    
    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        return repository.save(intent);
    }
    
    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
    
    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return repository.findAll();
    }
}



















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























package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {
    
    @Autowired
    private RewardRuleRepository repository;
    
    @Override
    public RewardRule createRule(RewardRule rule) {
        return repository.save(rule);
    }
    
    @Override
    public List<RewardRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
    
    @Override
    public List<RewardRule> getAllRules() {
        return repository.findAll();
    }
}



















package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import com.example.demo.config.SecurityConfig.SimplePasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    
    @Autowired
    private UserProfileRepository repository;
    
    @Autowired
    private SimplePasswordEncoder passwordEncoder;
    
    @Override
    public UserProfile createUser(UserProfile user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID().toString());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
    
    @Override
    public UserProfile getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    @Override
    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }
}








































