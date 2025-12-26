package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    UserProfile save(UserProfile userProfile);
    UserProfile createUser(UserProfile userProfile);
    UserProfile getUserById(Long id);
    Optional<UserProfile> findById(Long id);
    List<UserProfile> findAll();
    List<UserProfile> getAllUsers();
    void deleteById(Long id);
}
