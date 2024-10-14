package com.SchoolWebSite.Security;

import com.SchoolWebSite.Models.Student; // Adjust this import based on your project structure
import com.SchoolWebSite.Repository.StudentRepo; // Import your repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo; // Inject your student repository

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepo.findByEmail(email); // Adjust according to your method for fetching student

        if (student == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                student.getEmail(), // Username (email)
                student.getPassword(), // Password
                student.getAuthorities() // Authorities (roles)
        );
    }
}
