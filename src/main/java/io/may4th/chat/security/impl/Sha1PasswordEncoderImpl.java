package io.may4th.chat.security.impl;

import io.may4th.chat.security.api.PasswordEncoder;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
public class Sha1PasswordEncoderImpl extends BaseCoder implements PasswordEncoder {

    private static final String ALGORITHM = "SHA-1";
    private static final int SALT_LENGTH = 32;

    public Sha1PasswordEncoderImpl() {
        super(ALGORITHM);
    }

    private byte[] join(byte[] a, byte[] b) {
        val result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    private byte[] salt() {
        val data = new byte[SALT_LENGTH];
        new Random().nextBytes(data);
        return data;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword.length() == 0) {
            throw new IllegalArgumentException();
        }
        val salt = salt();
        val hash = hash(salt, rawPassword.toString().getBytes());
        return encode(join(salt, hash));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (encodedPassword.length() == 0) {
            throw new IllegalArgumentException();
        }
        val pass = decode(encodedPassword);
        val salt = Arrays.copyOfRange(pass, 0, SALT_LENGTH);
        val hash = Arrays.copyOfRange(pass, SALT_LENGTH, pass.length);
        return Arrays.equals(hash, hash(salt, rawPassword.toString().getBytes()));
    }
}
