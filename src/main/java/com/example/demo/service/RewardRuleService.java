package com.example.demo.service;

import com.example.demo.entity.RewardRule;
import java.util.List;
import java.util.Optional;

public interface RewardRuleService {
    RewardRule save(RewardRule rewardRule);
    RewardRule createRule(RewardRule rewardRule);
    Optional<RewardRule> findById(Long id);
    List<RewardRule> findAll();
    List<RewardRule> getAllRules();
    List<RewardRule> getActiveRules();
    void deleteById(Long id);
    List<RewardRule> findActiveRules();
}
