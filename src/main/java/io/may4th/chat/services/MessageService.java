package io.may4th.chat.services;

import io.may4th.chat.services.tos.MessageTO;
import io.may4th.chat.services.tos.NewMessageTO;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    List<MessageTO> findAllByRoomId(UUID roomId);

    MessageTO save(NewMessageTO newMessageTO);
}
