package com.example.demo.service.implement;

import com.example.demo.Entity.PurchaseIntentRecord;
import com.example.demo.Repository.PurchaseIntentRecordRepository;
import com.example.demo.Service.PurchaseIntentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentServiceImplement implements PurchaseIntentService {

    private final PurchaseIntentRepository repository;

    
    public PurchaseIntentServiceImplement(PurchaseIntentRepository repository) {
        this.repository = repository;
    }
}