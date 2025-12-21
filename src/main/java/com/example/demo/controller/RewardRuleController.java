package com.example.demo.controller;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reward-rules")
public class RewardRuleController {

    private final RewardRuleService service;

    public RewardRuleController(RewardRuleService service) {
        this.service = service;
    }

    @PostMapping
    public RewardRule createRule(@RequestBody RewardRule rule) {
        return service.createRule(rule);
    }

    @GetMapping
    public List<RewardRule> getAllRules() {
        return service.getAllRules();
    }
}
