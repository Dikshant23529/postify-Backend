package com.postify.blog.model;

import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Document(collection = "users")
public class RegisterUser {

    @Id
    private String id;

    @NotNull(message = "something went wrong")
    private String userId;

    @Email(message = "Please provide a valid email")
    @Indexed(unique = true)
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain atleast 8 characters, one uppercase, one lowercase, one number and one special character")
    @Size(min = 8, max = 30, message = "Password must contain atleast 8 characters")
    private String password;

    private String role;

    private boolean active;

    private boolean verified;

    private String modeOfRegisteration;

    private String refreshToken;

    private boolean twoFactorEnabled;

    private boolean isAccountNonExpired;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    @Future(message = "Date of registration cannot be in the past")
    private LocalDateTime dateOfRegistration;

}
