package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {
    private final RewardRuleRepository rewardRuleRepository;

    public RewardRuleServiceImpl(RewardRuleRepository rewardRuleRepository) {
        this.rewardRuleRepository = rewardRuleRepository;
    }

    @Override
    public RewardRule save(RewardRule rewardRule) {
        return rewardRuleRepository.save(rewardRule);
    }

    @Override
    public RewardRule createRule(RewardRule rewardRule) {
        return rewardRuleRepository.save(rewardRule);
    }

    @Override
    public Optional<RewardRule> findById(Long id) {
        return rewardRuleRepository.findById(id);
    }

    @Override
    public List<RewardRule> findAll() {
        return rewardRuleRepository.findAll();
    }

    @Override
    public List<RewardRule> getAllRules() {
        return rewardRuleRepository.findAll();
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return rewardRuleRepository.findByActiveTrue();
    }

    @Override
    public void deleteById(Long id) {
        rewardRuleRepository.deleteById(id);
    }

    @Override
    public List<RewardRule> findActiveRules() {
        return rewardRuleRepository.findActiveRules();
    }
}