package com.SchoolWebSite.Security;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.SchoolWebSite.Models.Student;


public class BaseController {
public BaseController(){};
    public Student getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Student md= (Student) authentication.getPrincipal();
        return md;
    }
}