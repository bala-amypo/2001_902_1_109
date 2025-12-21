package com.example.demo.Service;

import com.example.demo.Entity.CreditCardRecord;

import java.util.List;

public interface CreditCardService {
    CreditCardRecord addCard(CreditCardRecord card);
    List<CreditCardRecord> getCardsByUser(Long userId);
}




























