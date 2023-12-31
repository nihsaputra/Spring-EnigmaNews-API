package com.enigma.enigmanews.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.model.JwtClaim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@Slf4j
public class JwtUtil {

    @Value("${app.enigma-news.jwt-secret}")
    private String secretKey;

    @Value("${app.enigma-news.jwt-expirationInSecond}")
    private long expitartionInSecond;

    @Value("${app.enigma-news.app-name}")
    private String appName;

    // Generate Token
    public String generateToken(UserCredential user){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            List<String> roles = user.getRoles()
                                        .stream()
                                         .map(role -> role.getRole().name())
                                          .toList();

            return JWT.create()
                    .withIssuer(appName)
                    .withSubject(user.getId())
                    .withClaim("roles",roles)
                    .withExpiresAt(Instant.now().plusSeconds(expitartionInSecond))
                    .sign(algorithm);

        }catch (JWTCreationException e){
            log.error("invalid generate token: {}",e.getMessage());
            return null;
        }
    }

    // Verifikasi Token
    public boolean VeriFyJwtToken(String token){
        try {
            Algorithm algorithm =Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT verify = verifier.verify(token);
            return verify.getIssuer().equals(appName);
        }catch (JWTVerificationException e){
            log.error("invalid verification JWT : {}",e.getMessage());
            return false;
        }
    }


    // Userinfo dari token
    public JwtClaim getUserInfoByToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT verify = verifier.verify(token);

            List<String> roles = verify.getClaim("roles").asList(String.class);

            return JwtClaim.builder()
                    .userId(verify.getSubject())
                    .roles(roles)
                    .build();
        }catch (JWTVerificationException e){
            log.error("invalid verification JWT: {}", e.getMessage());
            return null;
        }
    }


}
