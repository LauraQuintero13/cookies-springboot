package com.example.cookies.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // Clave secreta para firmar token
    private final SecretKey secretKey =
            Keys.hmacShaKeyFor(
                    "miclavesupersecreta123456789012345".getBytes()
            );

    public String generarToken(String usuario) {

        return Jwts.builder()

                .subject(usuario)

                .issuedAt(new Date())

                .expiration(
                        new Date(System.currentTimeMillis() + 3600000)
                )

                .signWith(secretKey)

                .compact();
    }

    public String extraerUsuario(String token) {

        Claims claims = Jwts.parser()

                .verifyWith(secretKey)

                .build()

                .parseSignedClaims(token)

                .getPayload();

        return claims.getSubject();
    }
}