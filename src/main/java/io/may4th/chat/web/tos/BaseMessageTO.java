package io.may4th.chat.web.tos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class BaseMessageTO {

    private UUID id;
    private UUID userId;
    private UUID roomId;
    private String body;
}
