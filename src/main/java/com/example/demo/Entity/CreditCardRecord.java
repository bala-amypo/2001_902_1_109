package com.example.demo.Entity;
import jakrta.persistence.*;
import java.time.*;
@Entity
public class CreditCardRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String fullName;
    private String email;
    
}