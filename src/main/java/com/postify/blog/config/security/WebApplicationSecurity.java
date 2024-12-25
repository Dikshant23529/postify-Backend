package com.postify.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.postify.blog.config.security.jwt.AuthEntryPointJwt;
import com.postify.blog.config.security.jwt.AuthJwtRequestTokenFilter;
import com.postify.blog.config.security.oauth.config.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class WebApplicationSecurity {

    @Autowired
    public UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    AuthJwtRequestTokenFilter authJwtRequestTokenFilter() {
        return new AuthJwtRequestTokenFilter();
    };

    @Autowired
    private AuthEntryPointJwt unAuthorizedHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        return new CustomOAuth2UserService();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/auth/**", "/api/blog/public/**", "/api/test/**",
                                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                        .permitAll()
                        .requestMatchers("/actuator/**").hasRole("SUPER_ADMIN")
                        .anyRequest().hasAnyRole("USER", "ADMIN", "SUPER_ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(oauth -> {

                })
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling.authenticationEntryPoint(unAuthorizedHandler))
                .addFilterBefore(authJwtRequestTokenFilter(),
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
