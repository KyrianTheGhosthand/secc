package com.example.demosale.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

public class JwtAuthToken extends PreAuthenticatedAuthenticationToken {
    public JwtAuthToken(Object aPrincipal, Object aCredentials) {
        super(aPrincipal, aCredentials);
    }

    public JwtAuthToken(
            Object aPrincipal,
            Object aCredentials,
            Collection<? extends GrantedAuthority> anAuthorities
    ) {
        super(aPrincipal, aCredentials, anAuthorities);
    }
}
