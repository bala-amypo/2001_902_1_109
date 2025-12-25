package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Profile", description = "User Profile Management API")
public class UserProfileController {
    
    @Autowired
    private UserProfileService userService;
    
    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        UserProfile user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @Operation(summary = "Create new user")
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfile user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}