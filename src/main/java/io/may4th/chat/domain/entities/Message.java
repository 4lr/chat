package io.may4th.chat.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;
import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class Message {

    @Id
    private UUID id;
    private UUID userId;
    private UUID roomId;
    private ZonedDateTime timestamp;
    private String body;
}
