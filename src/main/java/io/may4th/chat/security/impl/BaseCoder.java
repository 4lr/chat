package io.may4th.chat.security.impl;

import lombok.SneakyThrows;
import lombok.val;

import java.security.MessageDigest;
import java.util.Base64;

class BaseCoder {

    private final String algorithm;

    BaseCoder(String algorithm) {
        this.algorithm = algorithm;
    }

    String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    @SneakyThrows
    byte[] hash(byte[]... data) {
        val digest = MessageDigest.getInstance(algorithm);
        for (val chunk : data) {
            digest.update(chunk);
        }
        return digest.digest();
    }

}
