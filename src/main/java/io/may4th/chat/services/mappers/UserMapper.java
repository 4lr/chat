package io.may4th.chat.services.mappers;

import io.may4th.chat.domain.entities.User;
import io.may4th.chat.services.tos.NewUserTO;
import io.may4th.chat.services.tos.UserTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserTO, NewUserTO> {
}
