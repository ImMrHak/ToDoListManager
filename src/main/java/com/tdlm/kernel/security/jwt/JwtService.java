package com.tdlm.kernel.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.access-token-expiration-time}")
    private long jwtAccessExpiration;

    @Value("${security.jwt.refresh-token-expiration-time}")
    private long jwtRefreshExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        userDetails.getAuthorities().forEach(a -> extraClaims.put(a.getAuthority(), a));
        return generateToken(extraClaims, userDetails, jwtAccessExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("type", "refresh");
        return generateToken(extraClaims, userDetails, jwtRefreshExpiration);
    }


    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return buildToken(extraClaims, userDetails, expiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String username) {
        final String tokenUsername = extractUsername(token);
        final boolean isTokenExpired = isTokenExpired(token);

        return tokenUsername.equals(username) && !isTokenExpired;
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Collection<? extends GrantedAuthority> extractAuthorities(String token) {
        Claims claims = extractAllClaims(token);

        Set<GrantedAuthority> authorities = new HashSet<>();

        // Extract roles (keys starting with "ROLE_")
        claims.forEach((key, value) -> {
            if (key.startsWith("ROLE_") && value instanceof Map<?, ?> roleData) {
                authorities.add(new SimpleGrantedAuthority(key)); // Add role itself

                // Extract privileges from role
                Object privilegesObj = roleData.get("privileges");
                if (privilegesObj instanceof List<?> privilegesList) {
                    privilegesList.forEach(privilege -> {
                        if (privilege instanceof Map<?, ?> privilegeData) {
                            Object privilegeAuthority = privilegeData.get("authority");
                            if (privilegeAuthority instanceof String authority) {
                                authorities.add(new SimpleGrantedAuthority(authority));
                            }
                        }
                    });
                }
            }
        });

        return authorities;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
