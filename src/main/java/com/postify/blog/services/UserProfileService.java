package com.postify.blog.services;

import java.security.Principal;

import com.postify.blog.dto.user.profile.UserProfileRequestDto;
import com.postify.blog.dto.user.profile.UserProfileResponseDTO;

public interface UserProfileService {

    UserProfileResponseDTO addUserProfile(Principal principal, UserProfileRequestDto userProfileRequestDto);

    UserProfileResponseDTO getUserProfile(Principal principal);

    UserProfileResponseDTO updateUserProfile(Principal principal, UserProfileRequestDto userProfileRequestDto);

}
