package com.example.demo3.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Security configuration is being applied 61616161...");

        http
                .csrf(AbstractHttpConfigurer::disable)  // Adjusted syntax for disabling CSRF
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()  // Allow all requests without authentication

                );

        return http.build();
    }
}
