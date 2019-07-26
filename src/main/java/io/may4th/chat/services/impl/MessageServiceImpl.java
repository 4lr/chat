package io.may4th.chat.services.impl;

import io.may4th.chat.domain.api.MessageRepository;
import io.may4th.chat.domain.api.entities.Message;
import io.may4th.chat.services.api.MessageService;
import io.may4th.chat.services.api.tos.MessageTO;
import io.may4th.chat.services.api.tos.NewMessageTO;
import io.may4th.chat.services.impl.mappers.MessageMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl extends BaseService<Message, MessageTO, NewMessageTO> implements MessageService {

    @Autowired
    private final MessageMapper messageMapper;
    @Autowired
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper, MessageRepository messageRepository) {
        super(messageMapper, messageRepository);
        this.messageMapper = messageMapper;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageTO> findAllByRoomId(UUID roomId) {
        return messageMapper.to(messageRepository.findAllByRoomIdOrderByTimestamp(roomId));
    }

    @Override
    public MessageTO save(NewMessageTO newMessageTO) {
        val message = messageMapper.en(newMessageTO);
        message.setTimestamp(ZonedDateTime.now());
        return messageMapper.to(messageRepository.save(message));
    }
}
