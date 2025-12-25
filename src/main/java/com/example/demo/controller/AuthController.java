package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthController {
    
    @Autowired
    private UserProfileService userService;
    
    @Autowired
    private UserProfileRepository userRepository;
    
    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        UserProfile user = new UserProfile();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setActive(true);
        
        UserProfile saved = userService.createUser(user);
        String token = "mock.jwt.token";
        
        return ResponseEntity.ok(new JwtResponse(token, saved.getId(), saved.getEmail(), saved.getRole()));
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        UserProfile user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        
        String token = "mock.jwt.token";
        return ResponseEntity.ok(new JwtResponse(token, user.getId(), user.getEmail(), user.getRole()));
    }
}



















































































































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







