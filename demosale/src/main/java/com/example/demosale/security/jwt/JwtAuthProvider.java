package com.example.demosale.security.jwt;

import com.example.demosale.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtAuthProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof JwtAuthToken)) {
            return null;
        }
        var jwt = authentication.getCredentials().toString();
        var claims = extractClaimsFromJwt(jwt);
        validateClaims(claims);

        var username = claims.getSubject();
        var authoritiesClaim = claims.get("authorities", String.class);
        var authorities = Stream.of(authoritiesClaim.split(", "))
                .map(String::trim)
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new JwtAuthToken(username, jwt, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.isAssignableFrom(authentication);
    }

    private Claims extractClaimsFromJwt(String jwt) {
        try {
            return jwtService.extractAllClaims(jwt);
        } catch (JwtException ex) {
            throw new BadCredentialsException("Invalid token");
        }
    }

    private void validateClaims(Claims claims) {
        if (jwtService.isJwtExpired(claims.getIssuedAt())) {
            throw new BadCredentialsException("Expired Token");
        }
        final var REQUIRED_CLAIMS = List.of("sub", "authorities");
        for (var requiredClaim : REQUIRED_CLAIMS) {
            if (!claims.containsKey(requiredClaim)) {
                throw new BadCredentialsException("Token consists of invalid information");
            }
        }
    }
}
