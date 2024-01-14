package com.example.carhaulauthentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "system_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCarHaul {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "login")
    String login;
    @Column(name = "password")
    String password;
    @Column(name = "role")
    String role;

    public UserCarHaul(String fullName, String login, String password, String role) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserCarHaul{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
