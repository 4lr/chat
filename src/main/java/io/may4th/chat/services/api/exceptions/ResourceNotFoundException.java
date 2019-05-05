package io.may4th.chat.services.api.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, String value) {
        super(String.format("%s(id=%s)", resource, value));
    }

    public ResourceNotFoundException(String resource, String field, String value) {
        super(String.format("%s(%s='%s')", resource, field, value));
    }
}
