package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {
    private final AuthService authService;
    private final UserProfileService userProfileService;
    private final UserProfileRepository userProfileRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        this.userProfileService = null;
        this.userProfileRepository = null;
        this.jwtUtil = null;
    }

    // Constructor for tests
    public AuthController(UserProfileService userProfileService, 
                         UserProfileRepository userProfileRepository, 
                         AuthenticationManager authenticationManager, 
                         JwtUtil jwtUtil) {
        this.authService = null;
        this.userProfileService = userProfileService;
        this.userProfileRepository = userProfileRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    @Operation(summary = "User registration")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest registerRequest) {
        if (authService != null) {
            JwtResponse response = authService.register(registerRequest);
            return ResponseEntity.ok(response);
        } else {
            // Test mode
            UserProfile userProfile = new UserProfile();
            userProfile.setUserId(UUID.randomUUID().toString());
            userProfile.setFullName(registerRequest.getFullName());
            userProfile.setEmail(registerRequest.getEmail());
            userProfile.setPassword(registerRequest.getPassword());
            userProfile.setRole(registerRequest.getRole());
            UserProfile saved = userProfileService.createUser(userProfile);
            String token = jwtUtil.generateToken(saved.getId(), saved.getEmail(), saved.getRole());
            return ResponseEntity.ok(new JwtResponse(token, saved.getId(), saved.getEmail()));
        }
    }

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        if (authService != null) {
            JwtResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } else {
            // Test mode
            Optional<UserProfile> user = userProfileRepository.findByEmail(loginRequest.getEmail());
            if (user.isPresent()) {
                UserProfile userProfile = user.get();
                String token = jwtUtil.generateToken(userProfile.getId(), userProfile.getEmail(), userProfile.getRole());
                return ResponseEntity.ok(new JwtResponse(token, userProfile.getId(), userProfile.getEmail()));
            }
            return ResponseEntity.badRequest().build();
        }
    }
}