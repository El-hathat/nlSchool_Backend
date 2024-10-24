package com.SchoolWebSite.Security;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.SchoolWebSite.Models.Student;
import com.SchoolWebSite.Services.ClasseService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";
    private final String classe = "classe";
    private final String profil = "profil";
    private final String name = "name";

    @Autowired
    ClasseService serv;
    
    private Long TOKEN_VALIDITY = (long) 120000;

    private String TOKEN_SECRET="todoAPISecret1234";

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());
        claims.put(classe, serv.getClassesForCurrentYear(userDetails.getUsername()));
        claims.put(name, ((Student)userDetails).getFullName());
        claims.put(profil, ((Student)userDetails).getProfil());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        try {
            Claims claims = getClaims(token);
//System.out.println("user : "+claims.getSubject());
            return claims.getSubject();
        }catch (Exception ex) {
            return null;
        }
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    private Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex) {
            claims = null;
        }

        return claims;
    }
}
