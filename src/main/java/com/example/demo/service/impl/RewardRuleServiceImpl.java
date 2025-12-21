package com.example.demo.service.impl;

import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {

    @Override
    public int calculateReward(String category) {
        return 5;
    }
}
