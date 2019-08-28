package io.may4th.chat.web.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class WsMessage {

    private final UUID uuid = UUID.randomUUID();

    private final String destination;

    private final String type;

    private final Object body;
}
