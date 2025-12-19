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
    private String password;
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getUserId(){
        return userId;
    }
    
}