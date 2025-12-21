package com.example.demo.service.impl;

import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Override
    public String getUserType(Long userId) {
        return "PREMIUM";
    }
}
