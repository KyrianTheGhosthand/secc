package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public interface User extends UserDetails {

    @Override
    String getPassword();

    @Override
    String getUsername();
    @Override
    List<? extends GrantedAuthority> getAuthorities();


}
