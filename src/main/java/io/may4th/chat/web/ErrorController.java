package io.may4th.chat.web;

import io.may4th.chat.web.payload.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse error() {
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Unsupported operation");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
