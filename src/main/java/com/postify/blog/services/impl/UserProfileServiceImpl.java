package com.postify.blog.services.impl;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.postify.blog.dto.user.profile.UserProfileRequestDto;
import com.postify.blog.dto.user.profile.UserProfileResponseDTO;
import com.postify.blog.exceptions.ResourceNotFoundException;
import com.postify.blog.model.RegisterUser;
import com.postify.blog.model.UserProfile;
import com.postify.blog.repositories.UserProfileRepository;
import com.postify.blog.repositories.UserRegistrationRepository;
import com.postify.blog.services.UserProfileService;
import com.postify.blog.utils.model.mapper.UserModelMapper;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public UserProfileResponseDTO addUserProfile(Principal principal, UserProfileRequestDto userProfileRequestDto) {
        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found "));
        userProfileRequestDto.setUserProfileId(UUID.randomUUID().toString());
        userProfileRequestDto.setRegisterUserId(registerUser.getUserId());
        return UserModelMapper.mapToUserProfileResponseDto(
                userProfileRepository.save(UserModelMapper.mapToUserProfile(userProfileRequestDto)));
    }

    @Override
    public UserProfileResponseDTO getUserProfile(Principal principal) {
        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found "));
        UserProfile userProfile = userProfileRepository.findByRegisterUserId(registerUser.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("no User Profile Found By This User"));
        return UserModelMapper.mapToUserProfileResponseDto(userProfile);
    }

    @Override
    public UserProfileResponseDTO updateUserProfile(Principal principal, UserProfileRequestDto userProfileRequestDto) {
        RegisterUser registerUser = userRegistrationRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found "));
                UserProfile userProfile = userProfileRepository.findByRegisterUserId(registerUser.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("no User Profile Found By This User"));

        userProfile.setAddress(userProfileRequestDto.getAddress());
        userProfile.setDateOfBirth(userProfileRequestDto.getDateOfBirth());
        userProfile.setEducation(userProfileRequestDto.getEducation());
        userProfile.setExperience(userProfileRequestDto.getExperience());
        userProfile.setGitHubProfile(userProfileRequestDto.getGitHubProfile());
        userProfile.setLastName(userProfileRequestDto.getLastName());
        userProfile.setLinkedinProfile(userProfileRequestDto.getLinkedinProfile());
        userProfile.setMiddleName(userProfileRequestDto.getMiddleName());
        userProfile.setName(userProfileRequestDto.getName());
        userProfile.setOtherLinks(userProfileRequestDto.getOtherLinks());
        userProfile.setPhoneNo(userProfileRequestDto.getPhoneNo());
        userProfile.setPortFolioLink(userProfileRequestDto.getPortFolioLink());
        userProfile.setProfileImage(userProfileRequestDto.getProfileImage());
        userProfile.setUserProfileId(UUID.randomUUID().toString());

        return UserModelMapper.mapToUserProfileResponseDto(userProfile);
    }

}
