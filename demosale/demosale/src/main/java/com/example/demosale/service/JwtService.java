package com.example.demosale.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

import java.util.Date;

public interface JwtService {
    String generateJwt(Authentication auth);

    Claims extractAllClaims(String jwt);

    boolean isJwtExpired(Date issuedAt);
}
