package com.example.demo.controller;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {
    private final AuthService authService;
    private final UserProfileService userProfileService;
    private final UserProfileRepository userProfileRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
        this.userProfileService = null;
        this.userProfileRepository = null;
        this.jwtUtil = null;
    }

    // Constructor for tests
    public AuthController(UserProfileService userProfileService, 
                         UserProfileRepository userProfileRepository, 
                         AuthenticationManager authenticationManager, 
                         JwtUtil jwtUtil) {
        this.authService = null;
        this.userProfileService = userProfileService;
        this.userProfileRepository = userProfileRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    @Operation(summary = "User registration")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest registerRequest) {
        if (authService != null) {
            JwtResponse response = authService.register(registerRequest);
            return ResponseEntity.ok(response);
        } else {
            // Test mode
            UserProfile userProfile = new UserProfile();
            userProfile.setUserId(UUID.randomUUID().toString());
            userProfile.setFullName(registerRequest.getFullName());
            userProfile.setEmail(registerRequest.getEmail());
            userProfile.setPassword(registerRequest.getPassword());
            userProfile.setRole(registerRequest.getRole());
            UserProfile saved = userProfileService.createUser(userProfile);
            String token = jwtUtil.generateToken(saved.getId(), saved.getEmail(), saved.getRole());
            return ResponseEntity.ok(new JwtResponse(token, saved.getId(), saved.getEmail()));
        }
    }

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        if (authService != null) {
            JwtResponse response = authService.login(loginRequest);
            return ResponseEntity.ok(response);
        } else {
            // Test mode
            Optional<UserProfile> user = userProfileRepository.findByEmail(loginRequest.getEmail());
            if (user.isPresent()) {
                UserProfile userProfile = user.get();
                String token = jwtUtil.generateToken(userProfile.getId(), userProfile.getEmail(), userProfile.getRole());
                return ResponseEntity.ok(new JwtResponse(token, userProfile.getId(), userProfile.getEmail()));
            }
            return ResponseEntity.badRequest().build();
        }
    }
}







package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/creditcards")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    public CreditCardRecord addCard(@RequestBody CreditCardRecord card) {
        return creditCardService.addCard(card);
    }

    @GetMapping("/user/{userId}")
    public List<CreditCardRecord> getCardsByUser(@PathVariable Long userId) {
        return creditCardService.getCardsByUser(userId);
    }

    @GetMapping
    public List<CreditCardRecord> getAllCards() {
        return creditCardService.getAllCards();
    }
}










package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/intents")
public class PurchaseIntentController {
    private final PurchaseIntentService purchaseIntentService;

    public PurchaseIntentController(PurchaseIntentService purchaseIntentService) {
        this.purchaseIntentService = purchaseIntentService;
    }

    @PostMapping
    public PurchaseIntentRecord createIntent(@RequestBody PurchaseIntentRecord intent) {
        return purchaseIntentService.createIntent(intent);
    }

    @GetMapping("/user/{userId}")
    public List<PurchaseIntentRecord> getIntentsByUser(@PathVariable Long userId) {
        return purchaseIntentService.getIntentsByUser(userId);
    }

    @GetMapping
    public List<PurchaseIntentRecord> getAllIntents() {
        return purchaseIntentService.getAllIntents();
    }
}






















































































































































































































































































package com.example.demo.security;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserProfile> userProfile = userProfileRepository.findByEmail(email);
        
        if (userProfile.isPresent()) {
            UserProfile user = userProfile.get();
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(new ArrayList<>())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}







package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                email = jwtUtil.extractEmail(token);
            } catch (Exception e) {
                // Invalid token
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            
            if (jwtUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}










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