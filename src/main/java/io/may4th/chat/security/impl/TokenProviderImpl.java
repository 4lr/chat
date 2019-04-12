package io.may4th.chat.security.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.may4th.chat.security.api.TokenProvider;
import io.may4th.chat.security.api.UserDetails;
import io.may4th.chat.security.api.exceptions.AuthenticationException;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Random;

@Component
@Log4j2
public class TokenProviderImpl extends BaseCoder implements TokenProvider {

    private static final String ALGORITHM = "SHA-1";

    private final ObjectMapper objectMapper;

    @Value("${security.token.secret}")
    private String secret;

    @Value("${security.token.expms}")
    private long expms;

    @Autowired
    TokenProviderImpl(ObjectMapper objectMapper) {
        super(ALGORITHM);
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public String generateToken(UserDetails userDetails) {
        val now = System.currentTimeMillis();
        val authToken = new AuthToken()
            .setUserId(userDetails.getId())
            .setIssuedAt(System.currentTimeMillis())
            .setExpireAt(now + expms)
            .setSeed(new Random().nextLong());

        authToken.setSing(sing(authToken));

        return encode(objectMapper.writeValueAsString(authToken).getBytes());
    }

    AuthToken extractToken(String token) {
        try {
            return objectMapper.readValue(decode(token), AuthToken.class);
        } catch (Exception ex) {
            log.info("Exception", ex);
            throw new AuthenticationException();
        }
    }

    boolean isValid(AuthToken authToken) {
        return Objects.equals(authToken.getSing(), sing(authToken)) && System.currentTimeMillis() < authToken.getExpireAt();
    }

    private byte[] toBytes(long... longs) {
        val buffer = ByteBuffer.allocate(Long.SIZE * longs.length / Byte.SIZE);
        for (val value : longs) {
            buffer.putLong(value);
        }
        return buffer.array();
    }

    private String sing(AuthToken authToken) {
        return encode(hash(
            authToken.getUserId().getBytes(),
            toBytes(authToken.getIssuedAt(), authToken.getExpireAt(), authToken.getSeed()),
            secret.getBytes()
        ));
    }
}
