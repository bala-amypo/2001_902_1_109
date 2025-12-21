package com.example.demo.Service.Implement;

import com.example.demo.Entity.CreditCardRecord;
import com.example.demo.Repository.CreditCardRecordRepository;
import com.example.demo.Service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImplement implements CreditCardService {

    private final CreditCardRecordRepository repository;

    public CreditCardServiceImpl(CreditCardRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        return repository.save(card);
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}

























