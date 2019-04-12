package io.may4th.chat.security.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
class AuthToken {

    private String userId;

    private long issuedAt;

    private long expireAt;

    private long seed;

    private String sing;
}
