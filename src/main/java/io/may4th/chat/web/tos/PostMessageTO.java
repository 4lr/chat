package io.may4th.chat.web.tos;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@ApiModel
@Getter
@Setter
public class PostMessageTO extends BaseMessageTO {
}
