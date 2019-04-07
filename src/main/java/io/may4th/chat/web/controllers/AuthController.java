package io.may4th.chat.web.controllers;

import io.may4th.chat.security.CurrentUser;
import io.may4th.chat.security.JwtTokenProvider;
import io.may4th.chat.security.UserPrincipal;
import io.may4th.chat.services.UserService;
import io.may4th.chat.services.tos.NewUserTO;
import io.may4th.chat.web.payload.ApiErrorResponse;
import io.may4th.chat.web.payload.JwtAuthResponse;
import io.may4th.chat.web.payload.SignInRequest;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenProvider tokenProvider;

    @Autowired
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody @Valid SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticate(signInRequest.getUsername(), signInRequest.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid NewUserTO newUserTO) {
        userService.save(newUserTO);
        return ResponseEntity.ok(authenticate(newUserTO.getUsername(), newUserTO.getPassword()));
    }

    @GetMapping("/me")
    public UserPrincipal me(@ApiIgnore @CurrentUser UserPrincipal currentUser) {
        return currentUser;
    }

    private JwtAuthResponse authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return new JwtAuthResponse(token);
    }
}
