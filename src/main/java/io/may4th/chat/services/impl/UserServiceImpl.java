package io.may4th.chat.services.impl;

import io.may4th.chat.domain.api.UserRepository;
import io.may4th.chat.services.api.UserService;
import io.may4th.chat.services.api.exceptions.ResourceNotFoundException;
import io.may4th.chat.services.impl.mappers.UserMapper;
import io.may4th.chat.services.api.tos.NewUserTO;
import io.may4th.chat.services.api.tos.UserTO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserTO findById(UUID id) {
        return userMapper.to(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", id.toString())));
    }

    @Override
    public UserTO findByUsername(String username) {
        return userMapper.to(userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username)));
    }

    @Override
    public UserTO save(NewUserTO newUserTO) {
        val user = userMapper.en(newUserTO);
        user.setId(UUID.randomUUID());
        return userMapper.to(userRepository.save(user));
    }
}
