package io.may4th.chat.web.services;

import io.may4th.chat.security.api.PasswordEncoder;
import io.may4th.chat.security.api.TokenProvider;
import io.may4th.chat.security.api.UserDetailsService;
import io.may4th.chat.security.api.exceptions.AuthenticationException;
import io.may4th.chat.services.api.UserService;
import io.may4th.chat.services.api.tos.NewUserTO;
import io.may4th.chat.web.payload.AuthTokenResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    @Autowired
    private final TokenProvider tokenProvider;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final UserService userService;

    public AuthTokenResponse signup(String username, String password) {
        userService.save(
            new NewUserTO()
                .setUsername(username)
                .setHash(passwordEncoder.encode(password))
        );
        return authenticate(username, password);
    }

    public AuthTokenResponse authenticate(String username, String password) {
        val userDetails = userDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            val token = tokenProvider.generateToken(userDetails);
            return new AuthTokenResponse(token);
        }
        throw new AuthenticationException();
    }
}
