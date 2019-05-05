package io.may4th.chat.services.impl.mappers;

import io.may4th.chat.domain.api.entities.Message;
import io.may4th.chat.services.api.tos.MessageTO;
import io.may4th.chat.services.api.tos.NewMessageTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends BaseMapper<Message, MessageTO, NewMessageTO> {
}
