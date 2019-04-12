package io.may4th.chat.security.impl;

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
public class AuthenticationProvider {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private final TokenProviderImpl tokenProvider;

    @Autowired
    private final UserDetailsService userDetailsService;

    Optional<UserDetails> extractUserDetails(HttpServletRequest request) {
        return extractToken(request)
            .filter(StringUtils::hasText)
            .map(tokenProvider::extractToken)
            .filter(tokenProvider::isValid)
            .map(authToken -> userDetailsService.loadUserById(authToken.getUserId()));
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        return request != null ? Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER)) : Optional.empty();
    }
}
