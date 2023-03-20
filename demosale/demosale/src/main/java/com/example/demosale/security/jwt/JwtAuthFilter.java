package com.example.demosale.security.jwt;

import com.example.demosale.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final String JWT_HEADER = "Authorization";

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    private final ObjectMapper jacksonObjectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        var shouldContinue = checkAndAuthenticateRequest(request, response);
        if (shouldContinue) {
            filterChain.doFilter(request, response);
        }
    }

    private boolean checkAndAuthenticateRequest(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        if (isAuthenticated()) {
            return true;
        }
        var jwtHeader = Optional.ofNullable(request.getHeader(JWT_HEADER));
        if (jwtHeader.isEmpty()) {
            return true;
        }
        try {
            var jwt = extractJwtFromHeader(jwtHeader.get())
                    .orElseThrow(() -> new ApiException(401, "JWT không tồn tại"));
            var auth = doAuthenticate(jwt);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return true;
        } catch (ApiException ex) {
            doResponseError(response, ex);
            return false;
        }
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    private Optional<String> extractJwtFromHeader(String header) {
        final var HEADER_PREFIX = "Bearer ";
        if (!header.startsWith(HEADER_PREFIX)) {
            return Optional.empty();
        }
        return Optional.of(header.substring(HEADER_PREFIX.length()));
    }

    private Authentication doAuthenticate(String jwt) {
        try {
            var unauthenticatedToken = new JwtAuthToken(null, jwt); // Just 2 args
            return authenticationManager.authenticate(unauthenticatedToken);
        } catch (AuthenticationException ex) {
            throw new ApiException(401, ex.getMessage());
        }
    }

    private void doResponseError(HttpServletResponse response, ApiException errorResponse) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(errorResponse.getStatus());
        response.getWriter().print(jacksonObjectMapper.writeValueAsString(errorResponse));
    }
}
