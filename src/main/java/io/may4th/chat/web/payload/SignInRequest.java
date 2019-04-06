package io.may4th.chat.web.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class SignInRequest {

    @ApiModelProperty(required = true, example = "John")
    @NotBlank
    private String username;

    @ApiModelProperty(required = true, example = "pass")
    @NotBlank
    private String password;
}
