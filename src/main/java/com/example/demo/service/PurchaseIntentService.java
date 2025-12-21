package com.example.demo.service;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentService {

    private final PurchaseIntentRecordRepository repository;

    public PurchaseIntentService(PurchaseIntentRecordRepository repository) {
        this.repository = repository;
    }

    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        return repository.save(intent);
    }

    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}