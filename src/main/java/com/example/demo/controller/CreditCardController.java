package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Credit Card", description = "Credit Card Management API")
public class CreditCardController {
    
    @Autowired
    private CreditCardService cardService;
    
    @GetMapping
    @Operation(summary = "Get all credit cards")
    public ResponseEntity<List<CreditCardRecord>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get cards by user ID")
    public ResponseEntity<List<CreditCardRecord>> getCardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cardService.getCardsByUser(userId));
    }
    
    @PostMapping
    @Operation(summary = "Add new credit card")
    public ResponseEntity<CreditCardRecord> addCard(@RequestBody CreditCardRecord card) {
        return ResponseEntity.ok(cardService.addCard(card));
    }
}
