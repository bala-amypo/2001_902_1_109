package com.example.demo.service;

import com.example.demo.entity.CreditCardRecord;
import java.util.List;

public interface CreditCardService {
    List<CreditCardRecord> getCardsByUser(Long userId);
}
