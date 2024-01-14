package com.example.carhaulauthentication.service;

import com.example.carhaulauthentication.model.UserCarHaul;
import com.example.carhaulauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserCarHaul login(String login, String password){
        return userRepository.findByLoginAndPassword(login,password);
    }
    public UserCarHaul findByLogin(String login){
        return userRepository.findByLogin(login);
    }
}
