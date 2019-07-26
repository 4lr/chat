package io.may4th.chat.services.impl;

import io.may4th.chat.domain.api.UserRepository;
import io.may4th.chat.domain.api.entities.User;
import io.may4th.chat.services.api.UserService;
import io.may4th.chat.services.api.exceptions.ResourceNotFoundException;
import io.may4th.chat.services.api.tos.NewUserTO;
import io.may4th.chat.services.api.tos.UserTO;
import io.may4th.chat.services.impl.mappers.UserMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends BaseService<User, UserTO, NewUserTO> implements UserService {

    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        super(userMapper, userRepository);
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserTO findByUsername(String username) {
        return userMapper.to(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(userRepository.getClass().getSimpleName(), "username", username)));
    }

    @Override
    public UserTO save(NewUserTO newUserTO) {
        val user = userMapper.en(newUserTO);
        user.setId(UUID.randomUUID());
        return userMapper.to(userRepository.save(user));
    }
}
