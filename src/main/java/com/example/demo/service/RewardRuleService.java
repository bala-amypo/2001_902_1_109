package com.example.demo.service;

import com.example.demo.entity.RewardRule;
import com.example.demo.repository.RewardRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRuleService {

    private final RewardRuleRepository repository;

    public RewardRuleService(RewardRuleRepository repository) {
        this.repository = repository;
    }

    public RewardRule createRule(RewardRule rule) {
        return repository.save(rule);
    }

    public List<RewardRule> getAllRules() {
        return repository.findAll();
    }
}