package io.may4th.chat.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class Message {

    @Id
    @NotNull
    private UUID id;
    @NotNull
    private UUID userId;
    @NotNull
    private UUID roomId;
    @NotNull
    private ZonedDateTime timestamp;
    @NotEmpty
    @Length(min = 1, max = 4096)
    private String body;
}
