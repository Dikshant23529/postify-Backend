package com.postify.blog.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postify.blog.config.security.jwt.JwtUtils;
import com.postify.blog.dto.login.LoginRequestDTO;
import com.postify.blog.dto.login.LoginResponseDTO;
import com.postify.blog.dto.user.UserRequestDto;
import com.postify.blog.dto.user.UserResponseDto;
import com.postify.blog.model.RegisterUser;
import com.postify.blog.repositories.UserRegistrationRepository;
import com.postify.blog.services.impl.UserRegistrationServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserRegistrationServiceImpl userRegistrationServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRegistrationRepository registrationRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return ResponseEntity.ok(userRegistrationServiceImpl.registerUser(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (AuthenticationException authenticationException) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad Credentail");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        RegisterUser registerUser = registrationRepository.findByUsername(userDetails.getUsername()).get();

        if (registerUser.isActive()) {

            String jwt = jwtUtils.generateJwtTokenFromUsername(userDetails.getUsername());

            String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();

            LoginResponseDTO response = new LoginResponseDTO(userDetails.getUsername(), role, jwt, null);

            return ResponseEntity.ok(response);

        }

        Map<String, String> map = new HashMap<>();
        map.put("Username", userDetails.getUsername());
        map.put("status", "Not Active");
        return new ResponseEntity<>(map, HttpStatus.LOCKED);

    }

}
