package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile createUser(UserProfile user) {
        return repository.save(user);
    }

    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }

    public UserProfile getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }
}















package com.example.demo.service;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.repository.CreditCardRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    private final CreditCardRecordRepository repository;

    public CreditCardService(CreditCardRecordRepository repository) {
        this.repository = repository;
    }

    public CreditCardRecord addCard(CreditCardRecord card) {
        return repository.save(card);
    }

    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}






























































