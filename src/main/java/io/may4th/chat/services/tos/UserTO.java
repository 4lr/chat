package io.may4th.chat.services.tos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Accessors(chain = true)
@Getter
@Setter
public class UserTO {

    private UUID id;

    private String username;

    private String hash;
}
