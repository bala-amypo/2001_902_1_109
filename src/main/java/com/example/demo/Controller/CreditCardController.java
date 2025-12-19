
package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CreditCardController {

    private final CreditCardService service;

    public CreditCardController(CreditCardService service) {
        this.service = service;
    }

    @PostMapping
    public CreditCardRecord addCard(@RequestBody CreditCardRecord card) {
        return service.addCard(card);
    }

    @GetMapping("/user/{userId}")
    public List<CreditCardRecord> getCardsByUser(@PathVariable Long userId) {
        return service.getCardsByUser(userId);
    }
}



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


package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intents")
public class PurchaseIntentController {

    private final PurchaseIntentService service;

    public PurchaseIntentController(PurchaseIntentService service) {
        this.service = service;
    }

    @PostMapping
    public PurchaseIntentRecord createIntent(@RequestBody PurchaseIntentRecord intent) {
        return service.createIntent(intent);
    }

    @GetMapping("/user/{userId}")
    public List<PurchaseIntentRecord> getIntentsByUser(@PathVariable Long userId) {
        return service.getIntentsByUser(userId);
    }
}



package com.example.demo.controller;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @PostMapping("/{intentId}")
    public RecommendationRecord generateRecommendation(@PathVariable Long intentId) {
        return service.generateRecommendation(intentId);
    }

    @GetMapping("/user/{userId}")
    public List<RecommendationRecord> getRecommendationsByUser(@PathVariable Long userId) {
        return service.getRecommendationsByUser(userId);
    }
}