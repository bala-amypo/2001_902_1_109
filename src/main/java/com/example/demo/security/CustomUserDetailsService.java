package com.example.demo.security;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserProfile> userProfile = userProfileRepository.findByEmail(email);
        
        if (userProfile.isPresent()) {
            UserProfile user = userProfile.get();
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(new ArrayList<>())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}