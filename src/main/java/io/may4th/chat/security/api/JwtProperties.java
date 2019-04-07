package io.may4th.chat.security.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtProperties {

    private final String secret;

    private final long expms;
}
