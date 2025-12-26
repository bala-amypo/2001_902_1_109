package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Profile", description = "User Profile management APIs")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    @Operation(summary = "Create user")
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfile userProfile) {
        return ResponseEntity.ok(userProfileService.save(userProfile));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        return userProfileService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "List all users")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Activate/deactivate user")
    public ResponseEntity<UserProfile> updateUserStatus(@PathVariable Long id, @RequestParam Boolean active) {
        return userProfileService.findById(id)
                .map(user -> {
                    user.setActive(active);
                    return ResponseEntity.ok(userProfileService.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lookup/{userid}")
    @Operation(summary = "Lookup user")
    public ResponseEntity<UserProfile> lookupUser(@PathVariable String userid) {
        return userProfileService.findAll().stream()
                .filter(user -> userid.equals(user.getUserId()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}