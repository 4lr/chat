package io.may4th.chat.domain.api;

import io.may4th.chat.domain.api.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends MongoRepository<Message, UUID> {

    List<Message> findAllByRoomIdOrderByTimestamp(UUID roomId);
}
