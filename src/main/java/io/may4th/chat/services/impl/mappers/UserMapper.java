package io.may4th.chat.services.impl.mappers;

import io.may4th.chat.domain.api.entities.User;
import io.may4th.chat.services.api.tos.NewUserTO;
import io.may4th.chat.services.api.tos.UserTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserTO, NewUserTO> {
}
