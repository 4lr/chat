package io.may4th.chat.services;

import io.may4th.chat.services.tos.NewUserTO;
import io.may4th.chat.services.tos.UserTO;

import java.util.UUID;

public interface UserService {

    UserTO findById(UUID id);

    UserTO findByUsername(String username);

    UserTO save(NewUserTO newUserTO);
}
