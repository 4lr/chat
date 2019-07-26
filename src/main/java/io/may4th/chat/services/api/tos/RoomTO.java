package io.may4th.chat.services.api.tos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class RoomTO {

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000146")
    private UUID id;

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000147")
    private UUID ownerId;

    @ApiModelProperty(required = true, example = "NY2020")
    private String title;
}
