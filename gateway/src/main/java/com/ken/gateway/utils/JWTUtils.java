package com.ken.gateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class JWTUtils {

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
            return false;
        }
        return decodedJWT != null;
    }
}
