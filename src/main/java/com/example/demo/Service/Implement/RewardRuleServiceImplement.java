package com.example.demo.Service.Implement;

import com.example.demo.Entity.RewardRule;
import com.example.demo.Repository.RewardRuleRepository;
import com.example.demo.Service.RewardRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRuleServiceImplement implements RewardRuleService {

    private final RewardRuleRepository repository;

    
    public RewardRuleServiceImplement(RewardRuleRepository repository) {
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
