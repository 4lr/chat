package io.may4th.chat.web.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@ApiModel
@Getter
public class JwtAuthResponse {

    @ApiModelProperty(required = true)
    private final String accessToken;

    @ApiModelProperty(required = true)
    private final String tokenType = "Bearer";
}
