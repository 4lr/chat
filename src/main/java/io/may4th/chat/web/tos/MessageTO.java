package io.may4th.chat.web.tos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Accessors(chain = true)
@Getter
@Setter
public class MessageTO extends BaseMessageTO {

    private ZonedDateTime timestamp;
}
