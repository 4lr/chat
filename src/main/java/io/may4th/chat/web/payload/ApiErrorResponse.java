package io.may4th.chat.web.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@ApiModel
@Getter
public class ApiErrorResponse {

    @ApiModelProperty(required = true, example = "BAD_REQUEST")
    private final HttpStatus status;

    @ApiModelProperty(required = true)
    private final String message;

    @ApiModelProperty(required = true)
    private List<String> errors = Collections.emptyList();

    public ApiErrorResponse(HttpStatus status, String message, String error) {
        this(status, message, Collections.singletonList(error));
    }

    public ApiErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
