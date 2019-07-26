package io.may4th.chat.services.api;

import io.may4th.chat.services.api.tos.NewUserTO;
import io.may4th.chat.services.api.tos.UserTO;

public interface UserService extends BaseService<UserTO, NewUserTO> {

    UserTO findByUsername(String username);
}
