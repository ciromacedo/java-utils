package org.macedo.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.macedo.security.apikey.ApiKeyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtUtil jwtUtil;
    private final ApiKeyValidator apiKeyValidator;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, ApiKeyValidator apiKeyValidator) {
        this.jwtUtil = jwtUtil;
        this.apiKeyValidator = apiKeyValidator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String apiKey = request.getHeader("X-API-Key");
        // 1) Tentativa de autenticação via API KEY
        if (apiKey != null && !apiKey.isBlank()) {
            if (apiKeyValidator.isValid(apiKey)) {
                String subject = apiKeyValidator.resolveSubject(apiKey);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(subject, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Valid API Key for subject: {}", subject);
                filterChain.doFilter(request, response);
                return;
            } else {
                log.warn("Invalid API Key");
            }
        }

        // 2) Tentativa de autenticação via JWT
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtil.isValid(token)) {
                Claims claims = jwtUtil.parseClaims(token);
                String username = claims.getSubject();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("Valid JWT token for user: {}", username);
            } else {
                log.warn("Invalid JWT token");
            }
        }
        filterChain.doFilter(request, response);
    }
}
