package com.example.demo.Service;

import com.example.demo.entity.UserProfile;
import java.util.List;

public interface UserProfileService {

    UserProfile createUser(UserProfile userProfile);

    List<UserProfile> getAllUsers();

    UserProfile getUserById(Long id);   /
}
