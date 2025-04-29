package com.example.backend.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthTokenProvider {
    private final CustomUserDetailsService customUserDetailsService;

    public AuthTokenProvider(String secretKey, long tokenExpiry, CustomUserDetailsService customUserDetailsService) {
        this.tokenExpiry = tokenExpiry;
        this.customUserDetailsService = customUserDetailsService;

        byte[] secretKeyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
    }

    private long tokenExpiry;
    private Key secretKey;


    public String createToken(CustomUserDetailsService.UserPrincipal userInfo) {
        Date expirationDate = new Date(new Date().getTime() + tokenExpiry);
        Claims claims = Jwts.claims();
        claims.put("username", userInfo.getUsername());
        claims.setSubject(String.valueOf(userInfo.getUserId()));

        return Jwts.builder()
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(expirationDate)
                .setClaims(claims)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            log.error("JWT expired");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid");
        }
        return false;
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody();
    }
}