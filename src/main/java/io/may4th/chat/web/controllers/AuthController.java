package io.may4th.chat.web.controllers;

import io.may4th.chat.security.api.CurrentUser;
import io.may4th.chat.security.api.PasswordEncoder;
import io.may4th.chat.security.api.Secured;
import io.may4th.chat.security.api.TokenProvider;
import io.may4th.chat.security.api.UserDetails;
import io.may4th.chat.security.api.UserDetailsService;
import io.may4th.chat.security.api.exceptions.AuthenticationException;
import io.may4th.chat.services.api.UserService;
import io.may4th.chat.services.api.tos.NewUserTO;
import io.may4th.chat.web.payload.ApiErrorResponse;
import io.may4th.chat.web.payload.AuthTokenResponse;
import io.may4th.chat.web.payload.SignInRequest;
import io.may4th.chat.web.payload.SignUpRequest;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@AllArgsConstructor
@RequestMapping("/api/auth")
@RestController
@ApiResponses({
    @ApiResponse(code = 400, message = "Bad Request", response = ApiErrorResponse.class)
})
public class AuthController {

    @Autowired
    private final TokenProvider tokenProvider;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final UserService userService;

    @PostMapping("/signin")
    public AuthTokenResponse signin(@RequestBody @Valid SignInRequest signInRequest) {
        return authenticate(signInRequest.getUsername(), signInRequest.getPassword());
    }

    @PostMapping("/signup")
    public AuthTokenResponse signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        val newUserTO = new NewUserTO();
        newUserTO
            .setUsername(signUpRequest.getUsername())
            .setHash(passwordEncoder.encode(signUpRequest.getPassword()));
        userService.save(newUserTO);
        return authenticate(signUpRequest.getUsername(), signUpRequest.getPassword());
    }

    @GetMapping("/me")
    @Secured
    public UserDetails me(@ApiIgnore @CurrentUser UserDetails currentUser) {
        return currentUser;
    }

    private AuthTokenResponse authenticate(String username, String password) {
        val userDetails = userDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            val token = tokenProvider.generateToken(userDetails);
            return new AuthTokenResponse(token);
        }
        throw new AuthenticationException();
    }
}
