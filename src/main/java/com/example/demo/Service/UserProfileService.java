package com.example.demo.Service;

import com.example.demo.Entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    UserProfile createUser(UserProfile user);
    List<UserProfile> getAllUsers();
    UserProfile getUserById(Long id);
}