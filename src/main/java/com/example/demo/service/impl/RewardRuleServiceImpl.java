package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {

    @Override
    public RewardRule createRule(RewardRule rule) {
        return rule;
    }

    @Override
    public List<RewardRule> getAllRules() {
        return new ArrayList<>();
    }
}
