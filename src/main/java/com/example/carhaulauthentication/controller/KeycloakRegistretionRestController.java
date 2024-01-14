package com.example.carhaulauthentication.controller;

import com.example.carhaulauthentication.model.UserCarHaul;
import com.example.carhaulauthentication.service.KeycloakUserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class KeycloakRegistretionRestController {
    private final KeycloakUserService keycloakUserService;
    @Autowired
    public KeycloakRegistretionRestController(KeycloakUserService keycloakUserService) {
        this.keycloakUserService = keycloakUserService;
    }

    @PostMapping
    public UserRepresentation createUser(@RequestBody UserCarHaul userCarHaul) {
        return keycloakUserService.createUser(userCarHaul);
    }


}
