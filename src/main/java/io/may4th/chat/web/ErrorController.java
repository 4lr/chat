package io.may4th.chat.web;

import io.may4th.chat.web.tos.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails error(WebRequest request) {
        return new ErrorDetails(ZonedDateTime.now(), "Unsupported operation", request.getDescription(false));
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
