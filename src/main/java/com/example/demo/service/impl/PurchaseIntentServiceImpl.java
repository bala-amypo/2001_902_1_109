package com.example.demo.service.impl;

import com.example.demo.service.PurchaseIntentService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    @Override
    public void saveIntent(Long userId, String category) {
        // dummy logic
    }

    @Override
    public String getTopIntent(Long userId) {
        return "TRAVEL";
    }
}
