package com.postify.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.postify.blog.exceptions.ResourceNotFoundException;
import com.postify.blog.model.RegisterUser;
import com.postify.blog.repositories.UserRegistrationRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRegistrationRepository registrationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisterUser registerUser = registrationRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(username + " not found"));
        return new UserDetailsImpl(registerUser);
    }

}
