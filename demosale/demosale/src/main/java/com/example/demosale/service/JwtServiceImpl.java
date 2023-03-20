package com.example.demosale.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtServiceImpl implements JwtService{
    private final SignatureAlgorithm DEFAULT_SIGN_ALGORITHM = SignatureAlgorithm.HS256;
    private Key signingKey = Keys.secretKeyFor(DEFAULT_SIGN_ALGORITHM);
    private final long JWT_ACCESS_TOKEN_TTL = 8640000;

    @Override
    public String generateJwt(Authentication auth) {
        var authoritiesClaim = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));
        Map<String, String> claims = new HashMap<>();
        claims.put("authorities", authoritiesClaim);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(auth.getName()) // Đặt sau cùng để tránh bị ghi đè bởi các claim khác
                .setIssuedAt(new Date())
                .signWith(signingKey, DEFAULT_SIGN_ALGORITHM)
                .compact();
    }

    @Override
    public Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    @Override
    public boolean isJwtExpired(Date issuedAt) {
        if (issuedAt == null) {
            return true;
        }
        return issuedAt.getTime() + JWT_ACCESS_TOKEN_TTL < System.currentTimeMillis();
    }
}
