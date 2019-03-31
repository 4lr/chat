package io.may4th.chat.services.mappers;

import io.may4th.chat.domain.entities.Message;
import io.may4th.chat.services.tos.MessageTO;
import io.may4th.chat.services.tos.NewMessageTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends BaseMapper<Message, MessageTO, NewMessageTO> {
}
