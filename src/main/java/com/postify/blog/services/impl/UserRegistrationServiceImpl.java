package com.postify.blog.services.impl;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.postify.blog.dto.user.UserPasswordUpdateDto;
import com.postify.blog.dto.user.UserRequestDto;
import com.postify.blog.dto.user.UserResponseDto;
import com.postify.blog.exceptions.ResourceNotFoundException;
import com.postify.blog.model.RegisterUser;
import com.postify.blog.repositories.UserRegistrationRepository;
import com.postify.blog.services.UserRegistrationService;
import com.postify.blog.utils.model.mapper.UserModelMapper;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRequestDto userRequestDto) {
        RegisterUser registerUser = UserModelMapper.mapToRegisterUser(userRequestDto);
        registerUser.setActive(true);
        registerUser.setCredentialsNonExpired(true);
        registerUser.setAccountNonExpired(true);
        registerUser.setEnabled(true);
        registerUser.setTwoFactorEnabled(false);
        registerUser.setVerified(true);
        registerUser.setDateOfRegistration(LocalDateTime.now());
        registerUser.setUserId(UUID.randomUUID().toString());
        registerUser = userRegistrationRepository.save(registerUser);
        return UserModelMapper.mapToUserResponseDto(registerUser);
    }

    @Override
    public UserResponseDto getUserByUserId(Principal principal) {
        RegisterUser user = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with username: " + principal.getName()));
        RegisterUser registerUser = userRegistrationRepository.findByUserId(user.getUserId()).get();
        return UserModelMapper.mapToUserResponseDto(registerUser);
    }

    @Override
    public UserResponseDto getUserByUserName(Principal principal) {
        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName()).orElse(null);
        return UserModelMapper.mapToUserResponseDto(registerUser);
    }

    @Override
    public UserResponseDto updatePassword(Principal principal, UserPasswordUpdateDto userPasswordUpdateDto) {
        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with username: " + principal.getName()));
        registerUser.setPassword(passwordEncoder.encode(userPasswordUpdateDto.getPassword()));
        registerUser = userRegistrationRepository.save(registerUser);
        return UserModelMapper.mapToUserResponseDto(registerUser);
    }

    @Override
    public UserResponseDto deactivateUser(Principal principal) {
        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with username: " + principal.getName()));
        registerUser.setActive(false);
        registerUser = userRegistrationRepository.save(registerUser);
        return UserModelMapper.mapToUserResponseDto(registerUser);
    }

    // User Profile Methods 

}
