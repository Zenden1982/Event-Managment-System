package com.zenden.task_management_system.Autorization;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtils {
    @Value("f9JgP5qXwXmZTQmQ5zKc8v8h6ZTQbYqP9wRpL5cU5sA=")
    private String secret;

    @Value("30d")
    private Duration lifetime;


        private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
  
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).toList();
        claims.put("roles", roles);
        Date issudDate = new Date();
        Date expirationDate = new Date(issudDate.getTime() + lifetime.toMillis());
        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(issudDate)
                .expiration(expirationDate)
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getAllClaimFromToken(token);
        return claims.getSubject();
    }

    public List<String> getRolesFromToken(String token) {
        return getAllClaimFromToken(token).get("roles", List.class);
    }
    private Claims getAllClaimFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
