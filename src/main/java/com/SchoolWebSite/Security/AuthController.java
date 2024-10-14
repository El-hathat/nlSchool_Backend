package com.SchoolWebSite.Security;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolWebSite.Models.Student;
import com.SchoolWebSite.Services.StudentService;



@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {
        System.out.println("Email: " + signInRequest.getEmail());
        
        Student userDetails = studentService.getByEmail(signInRequest.getEmail());
        if (userDetails == null) {
            System.out.println("User not found!");
            throw new BadCredentialsException("Identifiants erronés"); // Lancez une exception appropriée
        }
        
        // Utilisez le bon champ pour le mot de passe
        String storedPassword = userDetails.getPassword(); // Assurez-vous que c'est le bon champ
        System.out.println("PWD: " + storedPassword); // Juste pour vérifier le mot de passe

        // Vérifiez si le mot de passe correspond
        if (!studentService.passwordEncoder().matches(signInRequest.getPassword(), storedPassword)) {
            System.out.println("Invalid password!");
            throw new BadCredentialsException("Identifiants erronés"); // Lancez une exception appropriée
        }

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            String token = tokenUtil.generateToken(userDetails);
            JwtResponse response = new JwtResponse(userDetails, token);
            
            System.out.println("Generated Token: " + token);
            
            return response;
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Authentication failed"); // Lancez une exception appropriée
        }
    }


}