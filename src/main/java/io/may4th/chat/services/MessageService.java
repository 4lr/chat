package io.may4th.chat.services;

import io.may4th.chat.web.tos.MessageTO;
import io.may4th.chat.web.tos.PostMessageTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public interface MessageService {

    List<MessageTO> findAllByRoomId(@NotNull UUID roomId);

    MessageTO save(@Valid PostMessageTO messageTO);
}
