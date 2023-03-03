package com.example.sec1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//Add this annotation to an @Configuration class to have the Spring Security configuration defined in any WebSecurityConfigurer
// or more likely by extending the WebSecurityConfigurerAdapter base class and overriding individual methods:
public class SecurityConfig {
    @Bean
//    Only one constructor of any given bean class may declare this annotation with the required() attribute set to true, indicating the constructor to autowire
//    when used as a Spring bean. Furthermore, if the required attribute is set to true, only a single constructor may be annotated with @Autowired.
//    If multiple non-required constructors declare the annotation, they will be considered as candidates for autowiring.
//    The constructor with the greatest number of dependencies that can be satisfied by matching beans in the Spring container will be chosen. If none of the candidates can be satisfied, then a primary/default constructor (if present) will be used. Similarly, if a class declares multiple constructors but none of them is annotated with @Autowired, then a primary/default constructor (if present) will be used. If a class only declares a single constructor to begin with, it will always be used, even if not annotated. An annotated constructor does not have to be public.
    public UserDetailsService userDetailsService(){
//      Core interface which loads user-specific data.
//      It is used throughout the framework as a user DAO(data access object)(responsible for encapsulating the details of the persistence layer
//      and providing a CRUD interface for a single entity) and is the strategy used by the DaoAuthenticationProvider.
        var user1 = User.withUsername("asdf").password("1234").authorities("read").build();
        var user2 = User.withUsername("asdfasdf").password("123456").authorities("read").build();

        return new InMemoryUserDetailsManager(user1, user2);
//       Non-persistent implementation of UserDetailsManager which is backed by an in-memory map.
//       Mainly intended for testing and demonstration purposes, where a full blown persistent system isn't required.
    }

    @Bean
//    Service interface for encoding passwords.
    public PasswordEncoder passwordEncoder(){
//        A password encoder that does nothing. Useful for testing where working with plain text passwords may be preferred
//        Get the singleton NoOpPasswordEncoder.
        return NoOpPasswordEncoder.getInstance();
    }
}
