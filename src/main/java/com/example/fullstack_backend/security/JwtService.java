package com.example.fullstack_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {

    private final String SECRET_KEY ="mysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";

    // ✅ Must return SecretKey (not generic Key)
    private SecretKey getSigning(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email,String role){
        return Jwts.builder()
                .subject(email)
                .claim("role",role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSigning())
                .compact();
    }

    public String validateToken(String token){
        return Jwts.parser()
                .verifyWith(getSigning())   // ✅ Now matches SecretKey
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public String extractEmail(String token){
        return Jwts.parser()
                .verifyWith(getSigning())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    public String extractRole(String token){
        return Jwts.parser()
                .verifyWith(getSigning())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

}
