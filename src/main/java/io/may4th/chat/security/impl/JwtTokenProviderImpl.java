package io.may4th.chat.security.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.may4th.chat.security.api.JwtProperties;
import io.may4th.chat.security.api.JwtTokenProvider;
import io.may4th.chat.security.api.UserDetails;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

    @Autowired
    private final JwtProperties jwtProperties;

    @Override
    public String generateToken(UserDetails userDetails) {
        val now = new Date();
        val expiryDate = new Date(now.getTime() + jwtProperties.getExpms());
        return Jwts.builder()
            .setSubject(userDetails.getId())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
            .compact();
    }
}
