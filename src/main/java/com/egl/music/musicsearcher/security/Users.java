package com.egl.music.musicsearcher.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class Users {
    @Id
    @Column
    @GeneratedValue
    int id;

    @Column(length = 100)
    String username;


    @Column(length = 100, unique = true)
    String email;

    @Column(length = 100)
    String password;

    public Users(String newUsername, String newEmail, String newPassword) {
        username = newUsername;
        email = newEmail;
        password = newPassword;
    }
}
