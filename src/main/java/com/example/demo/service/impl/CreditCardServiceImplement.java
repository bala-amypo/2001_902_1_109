package com.example.demo.service.implement;

import com.example.demo.Entity.CreditCardRecord;
import com.example.demo.Repository.CreditCardRecordRepository;
import com.example.demo.Service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImplement implements CreditCardService {

    private final CreditCardRepository repository;

    
    public CreditCardServiceImplement(CreditCardRepository repository) {
        this.repository = repository;
    }
}

























