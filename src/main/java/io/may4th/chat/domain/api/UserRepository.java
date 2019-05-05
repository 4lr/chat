package io.may4th.chat.domain.api;

import io.may4th.chat.domain.api.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findByUsername(String username);
}
