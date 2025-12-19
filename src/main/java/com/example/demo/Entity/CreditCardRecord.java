package com.example.demo.Entity;
import jakrta.persistence.*;
import java.time.*;
@Entity
public class CreditCardRecord{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String cardName;
    private String issuer;
    private String cardType;
    private String annualFee;
    private Boolean status;
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
    public void setUserId(String userId){
        this.userId=userId;
    }
    public String getCardName(){
        return cardName;
    }
    public void setCardName(String cardName){
        this.cardName=cardName;
    }
    public String getIssuer(){
        return issuer;
    }
    public void setIssuer(String issuer){
        this.issuer=issuer;
    }
    public String getCardType(){
        return cardType;
    }
    public void setCardType(String cardType){
        this.cardType=cardType;
    }
    public String getAnnualFee(){
        return annualFee;
    }
    public void setRole(String role){
        this.role=role;
    }
    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active=active;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreateAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

    public CreditCardRecord(Long id,String userId,String fullName,String email,String password,String role,Boolean active,LocakDateTime createdAt){
        this.id=id;
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.role=role;
        this.active=active;
        this.createdAt=createdAt;
    }
    public CreditCardRecord(){

    }
    
}