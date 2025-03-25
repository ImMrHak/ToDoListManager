package com.tdlm.kernel.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                String tokenType = jwtService.extractClaim(jwt, claims -> claims.get("type", String.class));
                boolean isRefreshToken = "refresh".equals(tokenType);
                Instant expirationTime = jwtService.extractClaim(jwt, claims -> claims.getExpiration().toInstant());
                boolean isTokenValid = jwtService.isTokenValid(jwt, userEmail);

                if (isRefreshToken && !request.getServletPath().equals("/api/v1/auth/refresh")) {
                    sendJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, "Invalid Token Usage", "Refresh token cannot be used to access resources", tokenType, expirationTime, userEmail);
                    return;
                }

                if (!isRefreshToken && !isTokenValid) {
                    sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token", "Invalid or expired token", tokenType, expirationTime, userEmail);
                    return;
                }

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userEmail,
                        null,
                        jwtService.extractAuthorities(jwt)
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            sendJsonResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Authentication Error", exception.getMessage(), null, null, null);
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }

    private void sendJsonResponse(HttpServletResponse response, int status, String error, String message, String tokenType, Instant expirationTime, String userEmail) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", error);
        responseBody.put("message", message);
        responseBody.put("tokenType", tokenType);
        responseBody.put("expiresAt", expirationTime != null ? expirationTime.toString() : null);
        responseBody.put("userEmail", userEmail);
        responseBody.put("timestamp", Instant.now().toString());
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
    }
}
