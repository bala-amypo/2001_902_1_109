package com.example.demo.controller;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class RewardRuleController {
    private final RewardRuleService rewardRuleService;

    public RewardRuleController(RewardRuleService rewardRuleService) {
        this.rewardRuleService = rewardRuleService;
    }

    @PostMapping
    public RewardRule createRule(@RequestBody RewardRule rule) {
        return rewardRuleService.createRule(rule);
    }

    @GetMapping
    public List<RewardRule> getAllRules() {
        return rewardRuleService.getAllRules();
    }

    @GetMapping("/active")
    public List<RewardRule> getActiveRules() {
        return rewardRuleService.getActiveRules();
    }
}