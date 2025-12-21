package com.example.demo.Service;

import com.example.demo.Entity.PurchaseIntentRecord;

import java.util.List;

public interface PurchaseIntentService {
    PurchaseIntentRecord createIntent(PurchaseIntentRecord intent);
    List<PurchaseIntentRecord> getIntentsByUser(Long userId);
}