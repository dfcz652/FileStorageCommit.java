package com.boots.config.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.boots.domain.User;
//import com.boots.service.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private String SECRET_KEY = "secret";

    Date date = new Date();

    public String generateJwtToken(Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, String.valueOf(algorithm)).compact();
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

}