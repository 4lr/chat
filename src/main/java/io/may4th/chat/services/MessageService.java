package io.may4th.chat.services;

import io.may4th.chat.web.tos.MessageTO;
import io.may4th.chat.web.tos.PostMessageTO;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    List<MessageTO> findAllByRoomId(UUID roomId);

    MessageTO save(PostMessageTO messageTO);
}
