package com.SchoolWebSite.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.SchoolWebSite.Services.StudentService;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // Optional, for method-level security
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {
            "/student/add",
            "/student/registrer",
            "/student/forgotpwd/{cne}/{tel}/{gmail}",
            "/api/v1/auth",
            "/student/hello",
            "/classes/*"
            
    };

    private final AuthFilter authFilter;
    private final CustomUserDetailsService customUserDetailsService; // Add this line

    public SecurityConfig(AuthFilter authFilter, CustomUserDetailsService customUserDetailsService) {
        this.authFilter = authFilter;
        this.customUserDetailsService = customUserDetailsService; // Inject the UserDetailsService
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .cors(Customizer.withDefaults())
        
        .csrf(csrf->csrf.disable())
        .authorizeHttpRequests()
        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()  // Permettre plusieurs chemins
        .anyRequest().authenticated();
                
            http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Define a PasswordEncoder bean
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
            .userDetailsService(customUserDetailsService) // Use the CustomUserDetailsService
            .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
    
    
    

}
