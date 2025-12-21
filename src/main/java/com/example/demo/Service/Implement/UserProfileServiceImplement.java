package com.example.demo.Service.Implement;

import com.example.demo.Entity.UserProfile;
import com.example.demo.Repository.UserProfileRepository;
import com.example.demo.Service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImplement implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfile createUser(UserProfile user) {
        return repository.save(user);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserProfile getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }
}