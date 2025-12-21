package com.example.myproject.Repository;

import com.example.myproject.Entity.CreditCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRecordRepository
        extends JpaRepository<CreditCardRecord, Long> {

    List<CreditCardRecord> findByUserId(Long userId);
}




package com.example.myproject.Repository;

import com.example.myproject.Entity.PurchaseIntentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseIntentRecordRepository
        extends JpaRepository<PurchaseIntentRecord, Long> {

    List<PurchaseIntentRecord> findByUserId(Long userId);
}




package com.example.myproject.Repository;

import com.example.myproject.Entity.RecommendationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRecordRepository
        extends JpaRepository<RecommendationRecord, Long> {

    List<RecommendationRecord> findByUserId(Long userId);
}




package com.example.myproject.Repository;

import com.example.myproject.Entity.RewardRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RewardRuleRepository extends JpaRepository<RewardRule, Long> {

    @Query("SELECT r FROM RewardRule r " +
           "WHERE r.cardId = :cardId " +
           "AND r.category = :category " +
           "AND r.active = true")
    List<RewardRule> findActiveRulesForCardCategory(
            @Param("cardId") Long cardId,
            @Param("category") String category
    );
}




package com.example.myproject.Repository;

import com.example.myproject.Entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}