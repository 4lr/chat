package io.may4th.chat.domain.api;

import io.may4th.chat.domain.api.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RoomRepository extends MongoRepository<Room, UUID> {
}
