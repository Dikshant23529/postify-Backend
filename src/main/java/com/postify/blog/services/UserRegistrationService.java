package com.postify.blog.services;


import java.security.Principal;

import com.postify.blog.dto.user.UserPasswordUpdateDto;
import com.postify.blog.dto.user.UserRequestDto;
import com.postify.blog.dto.user.UserResponseDto;

public interface UserRegistrationService {

    UserResponseDto registerUser(UserRequestDto userRequestDto);
    UserResponseDto getUserByUserId(Principal principal);
    UserResponseDto getUserByUserName(Principal principal);
    UserResponseDto updatePassword(Principal principal, UserPasswordUpdateDto userPasswordUpdateDto);
    UserResponseDto deactivateUser(Principal principal);

}
