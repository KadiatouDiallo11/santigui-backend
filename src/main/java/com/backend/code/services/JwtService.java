package com.backend.code.services;

import java.awt.RenderingHints.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.backend.code.entity.tables.Administrateur;
import com.backend.code.entity.tables.Utilisateur;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SECRET = "mySecretKeymySecretKeymySecretKey12345";

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(Utilisateur user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user instanceof Administrateur ? "ADMIN" : "EXPLOITANT")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}