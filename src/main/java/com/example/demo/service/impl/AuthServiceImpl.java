package com.example.demo.service.impl;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final UserProfileService userProfileService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserProfileService userProfileService, JwtUtil jwtUtil) {
        this.userProfileService = userProfileService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse register(RegisterRequest registerRequest) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(UUID.randomUUID().toString());
        userProfile.setFullName(registerRequest.getFullName());
        userProfile.setEmail(registerRequest.getEmail());
        userProfile.setPassword(registerRequest.getPassword());
        userProfile.setRole(registerRequest.getRole());
        
        UserProfile saved = userProfileService.createUser(userProfile);
        String token = jwtUtil.generateToken(saved.getId(), saved.getEmail(), saved.getRole());
        
        return new JwtResponse(token, saved.getId(), saved.getEmail());
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        // Simple implementation - in production, you'd validate credentials
        return new JwtResponse("dummy-token", 1L, loginRequest.getEmail());
    }
}
