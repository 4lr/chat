package io.may4th.chat.services.api;

import io.may4th.chat.services.api.tos.MessageTO;
import io.may4th.chat.services.api.tos.NewMessageTO;

import java.util.List;
import java.util.UUID;

public interface MessageService extends BaseService<MessageTO, NewMessageTO> {

    List<MessageTO> findAllByRoomId(UUID roomId);
}
