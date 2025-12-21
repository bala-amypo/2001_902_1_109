package com.example.demo.Service.Implement;

import com.example.demo.Entity.UserProfile;
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
}

























