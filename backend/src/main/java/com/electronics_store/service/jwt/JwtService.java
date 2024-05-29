package com.electronics_store.service.jwt;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.electronics_store.auth.userDetails.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expired}")
    private long EXPIRED_TOKEN;

    @Value("${application.security.jwt.refresh-token.expired}")
    private long EXPIRED_REFRESH_TOKEN;

    // tạo khóa bí mật dạng SHA
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(CustomUserDetails userDetails) {
        Map<String, String> claim = new HashMap<>();
        claim.put("email", userDetails.getEmail());
        return generateToken(claim, EXPIRED_TOKEN, userDetails);
    }

    public String generateRefreshToken(CustomUserDetails userDetails) {
        Map<String, String> claim = new HashMap<>();
        claim.put("account_id", userDetails.getId().toString());
        return generateToken(claim, EXPIRED_REFRESH_TOKEN, userDetails);
    }

    public String generateToken(Map<String, String> extractClaims, long expired, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extractClaims)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String extractEmail(String token) {
        return extractClaims(token, claims -> (String) claims.get("email"));
    }

    public String extractAccountId(String token) {
        return extractClaims(token, claims -> (String) claims.get("account_id"));
    }

    public boolean isTokenValid(String token, CustomUserDetails userDetails) {
        String email = extractEmail(token);
        return userDetails.getEmail().equals(email) && !isExpiration(token);
    }

    public boolean isRefreshTokenValid(String token, CustomUserDetails userDetails) {
        String accountId = extractAccountId(token);
        return (userDetails.getId().toString().equals(accountId)) && !isExpiration(token);
    }

    public boolean isExpiration(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
