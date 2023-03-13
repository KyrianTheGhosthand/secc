package vn.techmaster.simpleauthen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
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
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
        http.formLogin();
        http.authorizeRequests()
                .antMatchers("/api/products").hasAnyRole(Role.USER, Role.OPERATOR, Role.ADMIN)
                .antMatchers("/api/backoffice").hasAnyRole(Role.OPERATOR, Role.ADMIN)
                .antMatchers("/api/secret").hasRole(Role.ADMIN)
                .antMatchers("/**").permitAll();
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
        UserBuilder userBuilder = User.builder().passwordEncoder(encoder()::encode);
        var tho1 = userBuilder.username("tho1").password("123").roles(Role.USER).build();
        var tho2 = userBuilder.username("tho2").password("123").roles(Role.USER).build();
        var tho3 = userBuilder.username("tho3").password("123").roles(Role.USER).build();

        var operator = userBuilder.username("ope").password("123").roles(Role.OPERATOR).build();
        var boss = userBuilder.username("boss").password("123").roles(Role.ADMIN, Role.USER).build();

        System.out.println("password of tho3 = " + tho3.getPassword());

        users.add(tho1);
        users.add(tho2);
        users.add(tho3);
        users.add(operator);
        users.add(boss);

        return new InMemoryUserDetailsManager(users);
    }

}
