package io.may4th.chat.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class User {

    @Id
    @NotNull
    private UUID id;
    @NotBlank
    @Length(min = 4, max = 64)
    @Indexed(unique = true)
    private String username;
    @NotNull
    private String hash;
}
