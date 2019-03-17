package io.may4th.chat.web.tos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public class ApiError {

    private final HttpStatus status;
    private final String message;
    private List<String> errors = Collections.emptyList();

    public ApiError(HttpStatus status, String message, String error) {
        this(status, message, Collections.singletonList(error));
    }

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
