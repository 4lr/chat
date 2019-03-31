package io.may4th.chat.services.tos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class MessageTO {

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000046")
    @NotNull
    private UUID id;
    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000047")
    @NotNull
    private UUID userId;
    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000048")
    @NotNull
    private UUID roomId;
    @ApiModelProperty(required = true, example = "hello world!")
    @Length(min = 1, max = 4096)
    @NotEmpty
    private String body;
    @ApiModelProperty(required = true, example = "2019-03-17T17:35:28.776+03:00")
    @NotNull
    private ZonedDateTime timestamp;
}
