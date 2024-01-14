package com.example.carhaulauthentication.repository;

import com.example.carhaulauthentication.model.UserCarHaul;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserCarHaul,Long> {
    UserCarHaul findByLoginAndPassword(String login, String password);
    UserCarHaul findByLogin(String login);
}
