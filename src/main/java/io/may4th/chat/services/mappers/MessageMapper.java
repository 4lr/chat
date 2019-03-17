package io.may4th.chat.services.mappers;

import io.may4th.chat.domain.entities.Message;
import io.may4th.chat.web.tos.MessageTO;
import io.may4th.chat.web.tos.PostMessageTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper extends BaseMapper<Message, MessageTO, PostMessageTO> {
}
