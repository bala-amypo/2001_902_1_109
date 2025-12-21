package com.example.demo.Service.Implement;

import com.example.demo.entity.UserProfile;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.UserProfileService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImplement implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImplement(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile createUser(UserProfile userProfile) {
        return repository.save(userProfile);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserProfile getUserById(Long id) {   // ✅ FIXED
        return repository.findById(id).orElse(null);
    }
}
