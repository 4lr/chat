package io.may4th.chat.services.api.tos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class NewUserTO {

    private String username;

    private String hash;
}
