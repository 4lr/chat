package io.may4th.chat.services.api.tos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Getter
@Setter
public class NewUserTO {

    @Length(min = 4, max = 64)
    @NotBlank
    private String username;

    @NotNull
    private String hash;
}
