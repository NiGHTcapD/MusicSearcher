package com.egl.music.musicsearcher.security;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@Table(name = "auth")
@Entity
public class AuthGroup {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @Column
    String email;

    @NonNull
    @Column
    String role;

    public AuthGroup(String newEmail, String newRole) {
        email = newEmail;
        role = newRole;
    }
}
