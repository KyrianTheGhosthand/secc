package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin();
    }

    public static PasswordEncoder delegatePasswordEncoder(String encodingType) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingType, encoders);
    }

    @Bean
    public PasswordEncoder encoder() {
        return SecurityConfig.delegatePasswordEncoder("pbkdf2");
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Collection<UserDetails> users = new ArrayList<>();
        User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder()::encode);
        var a1 = userBuilder.username("a1").password("123").build();
        var a2 = userBuilder.username("a2").password("123").build();
        var a3 = userBuilder.username("a3").password("123").build();

        var operator = userBuilder.username("ope").password("123").build();
        var boss = userBuilder.username("boss").password("123").build();

        System.out.println("password of a1 = " + a1.getPassword());

        users.add(a1);
        users.add(a2);
        users.add(a3);
        users.add(operator);
        users.add(boss);

        return new InMemoryUserDetailsManager(users);
    }
}
