package com.ga.todoApplication.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JWTUtils {

    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    @Value("${jwt-secret}") //$ then whatever is the key in application.properties
    private String JwtSecret;

    @Value("${jwt-expiration-ms}")
    private int JwtExpiration;


    public String generateJwtToken(MyUserDetails myUserDetails) {
        return Jwts.builder()
                .setSubject((myUserDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JwtExpiration))
                .signWith(SignatureAlgorithm.HS256, JwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(JwtSecret).build().parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(JwtSecret).parsePlaintextJws(authToken);
            return true;
        } catch (SecurityException e){
            logger.log(Level.SEVERE,"Invalid JWT Signature: {0}",e.getMessage());
        }
        return false;
    }

}

