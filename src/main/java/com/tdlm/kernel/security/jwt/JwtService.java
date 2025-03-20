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

        Map<String, Object> authoritiesClaims = claims.entrySet()
                .stream()
                .filter(entry -> entry.getKey().startsWith("ROLE_") || entry.getKey().contains(":"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return authoritiesClaims.entrySet()
                .stream()
                .flatMap(entry -> {
                    if (entry.getValue() instanceof Map<?, ?> tempMap) {
                        Map<String, Object> privilege = new HashMap<>();
                        for (Map.Entry<?, ?> e : tempMap.entrySet()) {
                            if (e.getKey() instanceof String) {
                                privilege.put((String) e.getKey(), e.getValue());
                            }
                        }
                        return privilege.containsKey("authority") ?
                                Stream.of(new SimpleGrantedAuthority((String) privilege.get("authority"))) : Stream.empty();
                    }
                    return Stream.of(new SimpleGrantedAuthority(entry.getKey()));
                })
                .collect(Collectors.toList());
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
