package com.example.demo;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/signup", "/login", "/logout","/check").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin().disable()
            .logout().disable();

        return http.build();
    }
}
