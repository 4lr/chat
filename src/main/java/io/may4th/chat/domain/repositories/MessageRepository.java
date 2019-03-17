package io.may4th.chat.domain.repositories;

import io.may4th.chat.domain.entities.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends MongoRepository<Message, UUID> {

    List<Message> findAllByRoomIdOrderByTimestamp(UUID roomId);
}
