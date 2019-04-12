package io.may4th.chat.security.api;

public interface TokenProvider {

    String generateToken(UserDetails userDetails);
}
