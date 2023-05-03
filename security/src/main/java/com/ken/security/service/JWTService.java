package com.ken.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ken.security.entity.Student;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService {

    private final String secret = "123456";

    public boolean validateToken(String token) {
        DecodedJWT decodedJWT;
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            decodedJWT = JWT.require(algorithm)
                    .withIssuer("Ken")
                    .build()
                    .verify(token);
        }catch (JWTVerificationException exception){
            throw new RuntimeException("The token is not valid.");
        }
        return decodedJWT != null;
    }

    public String generateToken(Student student){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Ken")
                    .withSubject(student.getEmail())
                    .withClaim("id", student.getId())
                    .withExpiresAt(expirationTime())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException("The token could not be created.");
        }
    }

    public Instant expirationTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
