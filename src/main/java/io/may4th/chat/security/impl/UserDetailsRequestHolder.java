package io.may4th.chat.security.impl;

import io.may4th.chat.security.api.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDetailsRequestHolder {

    private final Optional<UserDetails> userDetails;

    @Autowired
    public UserDetailsRequestHolder(HttpServletRequest request, AuthenticationProvider authenticationProvider) {
        this.userDetails = authenticationProvider.extractUserDetails(request);
    }

    public Optional<UserDetails> getUserDetails() {
        return userDetails;
    }
}
