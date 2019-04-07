package io.may4th.chat.security.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.may4th.chat.security.api.JwtProperties;
import io.may4th.chat.security.api.UserDetails;
import io.may4th.chat.security.api.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
@Component
@Log4j2
public class JwtAuthenticationProvider {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private final JwtProperties jwtProperties;

    @Autowired
    private final UserDetailsService userDetailsService;

    Optional<UserDetails> extractUserDetails(HttpServletRequest request) {
        return extractToken(request)
            .filter(StringUtils::hasText)
            .filter(this::validateToken)
            .map(this::extractUserId)
            .map(userDetailsService::loadUserById);
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        return request != null ? extractToken(request.getHeader(AUTHORIZATION_HEADER)) : Optional.empty();
    }

    private Optional<String> extractToken(String header) {
        if (StringUtils.hasText(header) && header.startsWith(TOKEN_PREFIX)) {
            return Optional.of(header.substring(TOKEN_PREFIX.length()));
        }
        return Optional.empty();
    }

    private String extractUserId(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtProperties.getSecret())
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            log.info(ex.getClass().getName() + ": " + ex.getMessage());
        }
        return false;
    }
}
