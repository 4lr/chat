package io.may4th.chat.domain.api.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.UUID;

@Accessors(chain = true)
@Document
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

    @Length(min = 1, max = 4096)
    @NotBlank
    private String body;
}
