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
