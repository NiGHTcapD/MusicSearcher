package com.egl.music.musicsearcher.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmailAllIgnoreCase(String username);
}
