package com.example.carhaulauthentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@RequestMapping("api/v1")
public class HelloRestController {


    @GetMapping("/")
    public ResponseEntity<String> getAdmin(Principal principal) {
        return ResponseEntity.ok("Hello Admin \nUser Name : " + "userName" + "\nUser Email : " + "userEmail");
    }

    @GetMapping("/bay")
    public ResponseEntity<String> getUser(Principal principal) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userName = (String) token.getTokenAttributes().get("name");
        String userEmail = (String) token.getTokenAttributes().get("email");
        return ResponseEntity.ok("Hello User \nUser Name : " + userName + "\nUser Email : " + userEmail);
    }
}
