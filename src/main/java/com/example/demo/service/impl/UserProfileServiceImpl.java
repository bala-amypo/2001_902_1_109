package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import com.example.demo.config.SecurityConfig.SimplePasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    
    @Autowired
    private UserProfileRepository repository;
    
    @Autowired
    private SimplePasswordEncoder passwordEncoder;
    
    @Override
    public UserProfile createUser(UserProfile user) {
        if (user.getUserId() == null) {
            user.setUserId(UUID.randomUUID().toString());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
    
    @Override
    public UserProfile getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    @Override
    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }
}
