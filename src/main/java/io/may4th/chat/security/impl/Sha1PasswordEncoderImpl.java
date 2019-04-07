package io.may4th.chat.security.impl;

import io.may4th.chat.security.api.PasswordEncoder;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

@Component
public class Sha1PasswordEncoderImpl implements PasswordEncoder {

    private static final String ALGORITHM = "SHA-1";
    private static final int SALT_LENGTH = 32;


    private byte[] join(byte[] a, byte[] b) {
        val result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }


    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    private byte[] salt() {
        val data = new byte[SALT_LENGTH];
        new Random().nextBytes(data);
        return data;
    }

    @SneakyThrows
    private byte[] hash(byte[]... data) {
        val digest = MessageDigest.getInstance(ALGORITHM);
        for (val chunk : data) {
            digest.update(chunk);
        }
        return digest.digest();
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
