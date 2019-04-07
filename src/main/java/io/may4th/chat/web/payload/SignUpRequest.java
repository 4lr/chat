package io.may4th.chat.web.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class SignUpRequest {

    @ApiModelProperty(required = true, example = "John")
    @NotBlank
    @Length(min = 4, max = 64)
    private String username;

    @ApiModelProperty(required = true, example = "pass")
    @NotBlank
    @Length(min = 4, max = 64)
    private String password;
}
