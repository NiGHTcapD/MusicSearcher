package com.egl.music.musicsearcher.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal {
    Users users;
    List<AuthGroup> authGroup;


    public UserPrincipal(Users users, List<AuthGroup> authGroup) {
        this.users = users;
        this.authGroup = authGroup;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authGroup.stream().map( auth -> new SimpleGrantedAuthority(auth.getRole())).collect(Collectors.toList());
    }


    public String getPassword() {
        return users.getPassword();
    }


    public String getUsername() {
        return users.getEmail();
    }


    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }
}
