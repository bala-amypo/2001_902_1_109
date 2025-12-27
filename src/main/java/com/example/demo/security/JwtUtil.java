package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    
    private final SecretKey key;
    private final long expiration;
    
    public JwtUtil() {
        String secret = "mySecretKeymySecretKeymySecretKeymySecretKey";
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = 86400000; // 24 hours
    }
    
    public JwtUtil(byte[] secret, long expiration) {
        this.key = Keys.hmacShaKeyFor(secret);
        this.expiration = expiration;
    }
    
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
    
    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
    
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
    
    public String extractEmail(String token) {
        return getClaims(token).get("email", String.class);
    }
    
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }
    
    public Long extractUserId(String token) {
        return getClaims(token).get("userId", Long.class);
    }
    
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}