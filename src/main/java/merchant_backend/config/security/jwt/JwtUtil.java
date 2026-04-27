package merchant_backend.config.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import merchant_backend.entities.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expirationMs}")
    private int jwtExpirationMs; // e.g., 3600000 (1 hour)

    @Value("${app.jwt.refreshExpirationMs}")
    private int refreshExpirationMs; // e.g., 86400000 (24 hours)

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String username, Long userId, boolean loggedInOnce) {
        return generateToken(username, userId,loggedInOnce, jwtExpirationMs);
    }

    private String generateToken(String username, Long userId, boolean loggedInOnce,int expiration) {
        return Jwts.builder()
                .subject(username)
                .claim("id", userId)
                .claim("loggedInOnce", loggedInOnce)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the error (Expired, Malformed, etc.)
        }
        return false;
    }
}