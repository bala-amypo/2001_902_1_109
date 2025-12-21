package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import java.util.List;

public interface UserProfileService {

    UserProfile createUser(UserProfile userProfile);

    List<UserProfile> getAllUsers();

    UserProfile getUserById(Long id);   
}
