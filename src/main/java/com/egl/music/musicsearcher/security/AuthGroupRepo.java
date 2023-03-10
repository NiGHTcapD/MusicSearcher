package com.egl.music.musicsearcher.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthGroupRepo extends JpaRepository<AuthGroup, Integer> {

    List<AuthGroup> findByEmail(String email);
}
