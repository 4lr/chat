package io.may4th.chat.web.tos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
class BaseMessageTO {

    @NotNull
    private UUID id;
    @NotNull
    private UUID userId;
    @NotNull
    private UUID roomId;
    @NotEmpty
    @Length(min = 1, max = 4096)
    private String body;
}
