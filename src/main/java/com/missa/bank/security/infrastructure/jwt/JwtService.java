package com.missa.bank.security.infrastructure.jwt;

import com.missa.bank.security.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String secret;
    private final long expiration;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration}") long expiration
    ) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(User user) {

        Date now = new Date();

        Date expirationDate =
                new Date(now.getTime() + expiration);

        SecretKey key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .subject(user.getUsername().value())
                .claim("role", user.getRole().name())
                .claim("userId", user.getId())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token)
                .getSubject();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }
}