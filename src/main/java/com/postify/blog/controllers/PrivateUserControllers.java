package com.postify.blog.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postify.blog.dto.user.UserPasswordUpdateDto;
import com.postify.blog.dto.user.UserResponseDto;
import com.postify.blog.dto.user.profile.UserProfileRequestDto;
import com.postify.blog.dto.user.profile.UserProfileResponseDTO;
import com.postify.blog.services.impl.UserProfileServiceImpl;
import com.postify.blog.services.impl.UserRegistrationServiceImpl;

@RestController
@RequestMapping("/com/postify/blog/v0/user")
public class PrivateUserControllers {

    @Autowired
    private UserRegistrationServiceImpl userRegistrationServiceImpl;

    @Autowired
    private UserProfileServiceImpl userProfileServiceImpl;

    @GetMapping("/get/id")
    public ResponseEntity<UserResponseDto> getUserByUserId(Principal principal) {
        return ResponseEntity.ok(userRegistrationServiceImpl.getUserByUserId(principal));
    }

    @GetMapping("/get/username")
    public ResponseEntity<UserResponseDto> getUserByUserName(Principal principal) {
        return ResponseEntity.ok(userRegistrationServiceImpl.getUserByUserName(principal));
    }

    @PutMapping("/update/password")
    public ResponseEntity<UserResponseDto> updatePassword(Principal principal,
            @RequestBody UserPasswordUpdateDto userPasswordUpdateDto) {
        return ResponseEntity.ok(userRegistrationServiceImpl.updatePassword(principal, userPasswordUpdateDto));
    }

    @PutMapping("/deactivate")
    public ResponseEntity<UserResponseDto> registerUser(Principal principal) {
        return ResponseEntity.ok(userRegistrationServiceImpl.deactivateUser(principal));
    }

    // User Profile API's

    @PostMapping("/add/profile")
    public ResponseEntity<UserProfileResponseDTO> adduserProfileDetails(Principal principal,
            @RequestBody UserProfileRequestDto userProfileRequestDto) {
        return ResponseEntity.ok(userProfileServiceImpl.addUserProfile(principal, userProfileRequestDto));
    }

    @GetMapping("/profile/info")
    public ResponseEntity<UserProfileResponseDTO> getUserProfileDetails(Principal principal) {
        return ResponseEntity.ok(userProfileServiceImpl.getUserProfile(principal));
    }

    @PutMapping("/update/profile")
    public ResponseEntity<UserProfileResponseDTO> updateUserProfileDetails(Principal principal,
            UserProfileRequestDto userProfileRequestDto) {
        return ResponseEntity.ok(userProfileServiceImpl.updateUserProfile(principal, userProfileRequestDto));
    }

}
