package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SimplePasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }
    
    public static class SimplePasswordEncoder {
        public String encode(CharSequence rawPassword) {
            return rawPassword + "_ENC";
        }
        
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encodedPassword.equals(rawPassword + "_ENC");
        }
    }
}









