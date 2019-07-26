package io.may4th.chat.domain.api.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Accessors(chain = true)
@Document
@Getter
@Setter
public class Room {

    @Id
    @NotNull
    private UUID id;

    @NotNull
    private UUID ownerId;

    @Length(min = 1, max = 128)
    @NotBlank
    private String title;
}
