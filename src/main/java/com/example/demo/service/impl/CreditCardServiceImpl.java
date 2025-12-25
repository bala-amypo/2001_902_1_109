package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    
    @Autowired
    private CreditCardRecordRepository repository;
    
    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        return repository.save(card);
    }
    
    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
    
    @Override
    public List<CreditCardRecord> getAllCards() {
        return repository.findAll();
    }
}
