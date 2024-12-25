package com.postify.blog.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private String username;
    private String role;
    private String accessToken;
    private String refreshToken;

}
