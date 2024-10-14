package com.SchoolWebSite.Security;

import com.SchoolWebSite.Models.Student;

public class JwtResponse {

    Student med;
    private String token;

    public JwtResponse(Student med,String token) {
        this.med=med;
        this.token = token;
    }

    public Student getMed() {
        return med;
    }

    public void setMed(Student med) {
        this.med = med;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
