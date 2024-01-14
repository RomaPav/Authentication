package com.example.carhaulauthentication.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(((requests) -> requests
                .requestMatchers("/login","/register").permitAll()
//                .requestMatchers("/login","/register","/api/v1/").permitAll()
                .requestMatchers(HttpMethod.POST,"/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/v1/bay").hasAnyRole(USER)
                .anyRequest().authenticated()
        ));
        http.oauth2ResourceServer(oauth2 -> {
            oauth2.jwt(jwtConfigurer ->
                    jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter)
            );
        });
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }
}
