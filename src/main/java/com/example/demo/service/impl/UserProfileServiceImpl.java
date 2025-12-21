package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public UserProfile createUser(UserProfile user) {
        return user;
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return new ArrayList<>();
    }

    @Override
    public UserProfile getUserById(Long id) {
        return new UserProfile();
    }
}
