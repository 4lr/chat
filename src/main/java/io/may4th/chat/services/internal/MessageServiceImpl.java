package io.may4th.chat.services.internal;

import io.may4th.chat.domain.repositories.MessageRepository;
import io.may4th.chat.services.MessageService;
import io.may4th.chat.services.mappers.MessageMapper;
import io.may4th.chat.web.tos.MessageTO;
import io.may4th.chat.web.tos.PostMessageTO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private final MessageMapper messageMapper;
    @Autowired
    private final MessageRepository messageRepository;

    @Override
    public List<MessageTO> findAllByRoomId(@NotNull UUID roomId) {
        return messageMapper.to(messageRepository.findAllByRoomIdOrderByTimestamp(roomId));
    }

    @Override
    public MessageTO save(@Valid PostMessageTO messageTO) {
        val message = messageMapper.en(messageTO);
        message.setTimestamp(ZonedDateTime.now());
        return messageMapper.to(messageRepository.save(message));
    }
}
