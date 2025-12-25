package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/intents")
@Tag(name = "Purchase Intent", description = "Purchase Intent Management API")
public class PurchaseIntentController {
    
    @Autowired
    private PurchaseIntentService intentService;
    
    @GetMapping
    @Operation(summary = "Get all purchase intents")
    public ResponseEntity<List<PurchaseIntentRecord>> getAllIntents() {
        return ResponseEntity.ok(intentService.getAllIntents());
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get intents by user ID")
    public ResponseEntity<List<PurchaseIntentRecord>> getIntentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(intentService.getIntentsByUser(userId));
    }
    
    @PostMapping
    @Operation(summary = "Create new purchase intent")
    public ResponseEntity<PurchaseIntentRecord> createIntent(@RequestBody PurchaseIntentRecord intent) {
        return ResponseEntity.ok(intentService.createIntent(intent));
    }
}