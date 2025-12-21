package com.example.demo.Service;

import com.example.demo.Entity.CreditCardRecord;

import java.util.List;

public interface CreditCardService {
    CreditCardRecord addCard(CreditCardRecord card);
    List<CreditCardRecord> getCardsByUser(Long userId);
}







package com.example.myproject.Service;

import com.example.myproject.Entity.PurchaseIntentRecord;

import java.util.List;

public interface PurchaseIntentService {
    PurchaseIntentRecord createIntent(PurchaseIntentRecord intent);
    List<PurchaseIntentRecord> getIntentsByUser(Long userId);
}






package com.example.myproject.Service;

import com.example.myproject.Entity.RecommendationRecord;

import java.util.List;

public interface RecommendationService {
    RecommendationRecord generateRecommendation(Long intentId);
    List<RecommendationRecord> getRecommendationsByUser(Long userId);
}






package com.example.myproject.Service;

import com.example.myproject.Entity.RewardRule;

import java.util.List;

public interface RewardRuleService {
    RewardRule createRule(RewardRule rule);
    List<RewardRule> getAllRules();
}






package com.example.myproject.Service;

import com.example.myproject.Entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    UserProfile createUser(UserProfile user);
    List<UserProfile> getAllUsers();
    UserProfile getUserById(Long id);
}