package io.may4th.chat.web.tos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class MessageTO extends BaseMessageTO {

    @ApiModelProperty(required = true, example = "2019-03-17T17:35:28.776+03:00")
    @NotNull
    private ZonedDateTime timestamp;
}
