package com.postify.blog.config.security.oauth.config;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.postify.blog.model.RegisterUser;
import com.postify.blog.repositories.UserRegistrationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String password = UUID.randomUUID().toString();

        Optional<RegisterUser> existingUser = userRegistrationRepository.findByUsername(email);
    
        if (existingUser.isEmpty()) {
            RegisterUser registerUser = new RegisterUser();
            registerUser.setUserId(UUID.randomUUID().toString());
            registerUser.setUsername(email);
            registerUser.setPassword(password);
            registerUser.setActive(true);
            registerUser.setAccountNonExpired(true);
            registerUser.setCredentialsNonExpired(true);
            registerUser.setEnabled(true);
            registerUser.setRole("ROLE_USER");
            registerUser.setTwoFactorEnabled(false);
            registerUser.setModeOfRegisteration("GOOGLE");
            registerUser.setDateOfRegistration(LocalDateTime.now());
            registerUser.setRefreshToken(null);
            userRegistrationRepository.save(registerUser);
        }

        return oAuth2User;
    }

    public String getGoogleUserInfo(String token) {
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
        
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
