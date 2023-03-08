package com.example.sec1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired  //Phải nối vào InMemoryUserDetailsManager để tìm user theo Username
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired  //Dùng để kiểm tra password gửi lên trong login request với Hashed Password lưu trữ
    private PasswordEncoder encoder;

    @Override
    //Represents the token for an authentication request once the request has been process by Authentication Manager method.
//    Stored in thread-local SecurityContext by any authentication used. Takes care of managing the security context for you
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
//        Provide ID of the principal (username)
        String rawPassword = String.valueOf(authentication.getCredentials());

        try {
            UserDetails userDetail = inMemoryUserDetailsManager.loadUserByUsername(username); //Tìm UserDetail theo Username
            if (encoder.matches(rawPassword, userDetail.getPassword())) { //So sánh password
//An Authentication implementation that is designed for simple presentation of a username and password.
//The principal and credentials should be set with an Object that provides the respective property via its Object.toString() method. The simplest such Object to use is String.
                return new UsernamePasswordAuthenticationToken(username, userDetail.getPassword(), userDetail.getAuthorities());
            } else {
                return null;
            }
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }

    @Override // return true nếu CustomAuthenProvider hỗ trợ authentication kiểu Username, Password
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
