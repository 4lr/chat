package io.may4th.chat.security.api;

public interface JwtTokenProvider {

    String generateToken(UserDetails userDetails);
}
