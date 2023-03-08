package com.example.sec1.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.*;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("asdf").password("1234").authorities("read").and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
//    Basic authentication used base64 to encode the user credentials, while it has problems with storage sessions and in need of properly close and clear connection states,
//    for the other users cannot log back refreshing the browser. Form login is used to mitigate the problems mentioned above by basic authentication, by still using standard HTML to pass
//    username and password fields, then create a unique session that is tied to a unique key to connect between client and server on each "put" & "get" requests.
//    After the mentioned session the form requests the server render this unique key useless, which required another key (re-validation) when logging in a new session.
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic();
//        http.authorizeRequests().anyRequest().authenticated();
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic();
//        http.authorizeRequests().antMatchers("/**").authenticated();
//    }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            .authorizeRequests()
            .antMatchers("/products").permitAll()
            .antMatchers("/secret").hasRole("Admin").antMatchers("/knives").hasRole("Operator")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/login")
            .permitAll();
        }



    @Autowired
//    Interface for encoding passwords
    private PasswordEncoder encoder;
//  Encoding password based on prefixed identifier. The encoders below is using custom instance of password encoder.
    public static PasswordEncoder delegatePasswordEncoder(String encodingType){
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("MD4", new Md4PasswordEncoder());
        encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", new StandardPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingType, encoders);
    }
    @Bean
    public PasswordEncoder encoder() {
        return SecurityConfig.delegatePasswordEncoder("pbkdf2");
    }

    @Bean
//    Implimentation of UserDetailsManager which is backed by a memory map. Used mostly as testing materials/
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        Collection<UserDetails> users = new ArrayList<>();
        UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        var acc1 = User.withUsername("acc1").password("1234").authorities("USER").build();
        var acc2 = User.withUsername("acc2").password("1234").authorities("USER").build();
        var acc3 = User.withUsername("acc3").password("1234").authorities("USER").build();
        users.add(acc1);
        users.add(acc2);
        users.add(acc3);
        return new InMemoryUserDetailsManager(users);
    }
}
