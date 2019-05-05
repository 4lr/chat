package io.may4th.chat.services.api;

import io.may4th.chat.services.api.tos.NewUserTO;
import io.may4th.chat.services.api.tos.UserTO;

import java.util.UUID;

public interface UserService {

    UserTO findById(UUID id);

    UserTO findByUsername(String username);

    UserTO save(NewUserTO newUserTO);
}
