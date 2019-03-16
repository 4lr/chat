package io.may4th.chat.web.tos;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class ErrorDetails {
    private final ZonedDateTime timestamp;
    private final String message;
    private final String details;

    public ErrorDetails(ZonedDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
