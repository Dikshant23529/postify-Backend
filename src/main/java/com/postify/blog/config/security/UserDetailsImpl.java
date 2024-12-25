package com.postify.blog.config.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.postify.blog.model.RegisterUser;

public class UserDetailsImpl implements UserDetails {

    private RegisterUser registerUser;

    public UserDetailsImpl(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(registerUser.getRole());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
       return registerUser.getPassword();
    }

    @Override
    public String getUsername() {
       return registerUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return registerUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return registerUser.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return registerUser.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return registerUser.isEnabled();
    }

    public boolean twoFactorEnabled() {
        return registerUser.isTwoFactorEnabled();
    }

}
