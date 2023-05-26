package com.projetLocMns.ProjetFilRougeLocMnsV3.security;


import com.projetLocMns.ProjetFilRougeLocMnsV3.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    public User getUsager() {
        return user;
    }

    ///////////////////////////////////////////////
    // Voici toutes les méthodes implémentées

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

       return List.of(new SimpleGrantedAuthority(user.getRole().getRole()));

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}