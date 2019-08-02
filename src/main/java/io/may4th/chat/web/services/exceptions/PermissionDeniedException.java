package io.may4th.chat.web.services.exceptions;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException() {
        super();
    }

    public PermissionDeniedException(String msg) {
        super(msg);
    }

    public PermissionDeniedException(String msg, Throwable t) {
        super(msg, t);
    }
}
