package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class Authority {
    private List<GrantedAuthority> authorities;

    public Authority(){
        authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("READ"));
        authorities.add(new SimpleGrantedAuthority("CREATE"));
        authorities.add(new SimpleGrantedAuthority("DELETE"));
        authorities.add(new SimpleGrantedAuthority("EDIT"));
        authorities.add(new SimpleGrantedAuthority("SEARCH"));
    }
    public List<GrantedAuthority> getAuthorities(){return authorities;}

    public GrantedAuthority getAuthority(String authority){
        return authorities.stream()
                .filter(a -> a.getAuthority().equals(authority))
                .findFirst()
                .orElse(null);
    }
}
