package com.postify.blog.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    
    private String userId;
    private String username;
    private String password;
    private String role;

}
