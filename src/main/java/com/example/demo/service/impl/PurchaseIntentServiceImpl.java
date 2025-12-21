package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        return intent; // dummy logic
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return new ArrayList<>();
    }
}
