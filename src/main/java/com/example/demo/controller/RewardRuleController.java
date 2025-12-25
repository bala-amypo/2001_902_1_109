package com.example.demo.controller;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Reward Rule", description = "Reward Rule Management API")
public class RewardRuleController {
    
    @Autowired
    private RewardRuleService ruleService;
    
    @GetMapping
    @Operation(summary = "Get all reward rules")
    public ResponseEntity<List<RewardRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
    
    @GetMapping("/active")
    @Operation(summary = "Get active reward rules")
    public ResponseEntity<List<RewardRule>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }
    
    @PostMapping
    @Operation(summary = "Create new reward rule")
    public ResponseEntity<RewardRule> createRule(@RequestBody RewardRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }
}