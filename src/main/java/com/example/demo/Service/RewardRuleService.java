package com.example.demo.service;

import com.example.demo.Entity.RewardRule;

import java.util.List;

public interface RewardRuleService {
    RewardRule createRule(RewardRule rule);
    List<RewardRule> getAllRules();
}