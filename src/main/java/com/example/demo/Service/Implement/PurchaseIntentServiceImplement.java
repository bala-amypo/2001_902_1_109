package com.example.demo.Service.Implement;

import com.example.demo.Entity.PurchaseIntentRecord;
import com.example.demo.Repository.PurchaseIntentRecordRepository;
import com.example.demo.Service.PurchaseIntentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository repository;

    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        return repository.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}