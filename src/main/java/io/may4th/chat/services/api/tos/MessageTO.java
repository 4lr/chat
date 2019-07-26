package io.may4th.chat.services.api.tos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;
import java.util.UUID;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class MessageTO {

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000046")
    private UUID id;

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000047")
    private UUID userId;

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000048")
    private UUID roomId;

    @ApiModelProperty(required = true, example = "hello world!")
    private String body;

    @ApiModelProperty(required = true, example = "2019-03-17T17:35:28.776+03:00")
    private ZonedDateTime timestamp;
}
