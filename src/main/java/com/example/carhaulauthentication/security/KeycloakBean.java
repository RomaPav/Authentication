package com.example.carhaulauthentication.security;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakBean {
    @Bean
    public Keycloak keycloak(){

        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("carhaul")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("admin-cli")
                .clientSecret("sRqZHJ46li6CWI9hMGMbUlR51zNIiQ3o")
                .build();
    }

}
