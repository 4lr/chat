package io.may4th.chat.domain.api.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Accessors(chain = true)
@Document
@Getter
@Setter
public class User {

    @Id
    @NotNull
    private UUID id;

    @Indexed(unique = true)
    @Length(min = 4, max = 64)
    @NotBlank
    private String username;

    @NotNull
    private String hash;

    @NotNull
    private List<UUID> rooms;
}
