package com.example.demo.service;

public interface PurchaseIntentService {

    void saveIntent(Long userId, String category);

    String getTopIntent(Long userId);
}
