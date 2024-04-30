package com.ua.itclusterjava2024.service.implementation;

import com.ua.itclusterjava2024.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${token.access.secret}")
    private String accessTokenSecret;

    @Value("${token.access.expiration}")
    private Long accessTokenExpirationTime;

    @Value("${token.refresh.expiration}")
    private Long refreshTokenExpirationTime;


    // Generates token
    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("role", customUserDetails.getRole().getName());
            claims.put("tokenType", "access");
        }
        return buildToken(claims, userDetails, accessTokenExpirationTime);
    }

    // Generates refresh token
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("role", customUserDetails.getRole().getName());
            claims.put("tokenType", "refresh");
        }
        return buildToken(claims, userDetails, refreshTokenExpirationTime);
    }


    // Generates token with additional claims
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Long expirationMinutes) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
//                .issuer(issuer)
                .expiration(Date.from(Instant.now().plus(expirationMinutes, ChronoUnit.MINUTES)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean isTokenValidAndNotExpired(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()));
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isRefreshToken(String token) {
        return extractClaim(token, claims -> claims.get("tokenType", String.class)).equals("refresh");
    }

    public boolean isAccessToken(String token) {
        return extractClaim(token, claims -> claims.get("tokenType", String.class)).equals("access");
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(Date.from(Instant.now()));

    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    // Extracts all data from token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Gets signing key for generating token
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(accessTokenSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}