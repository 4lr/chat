package io.may4th.chat.services.internal;

import io.may4th.chat.domain.entities.User;
import io.may4th.chat.domain.repositories.UserRepository;
import io.may4th.chat.services.UserService;
import io.may4th.chat.services.exceptions.ResourceNotFoundException;
import io.may4th.chat.services.mappers.UserMapper;
import io.may4th.chat.services.tos.NewUserTO;
import io.may4th.chat.services.tos.UserTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserMapper userMapper;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserTO findById(UUID id) {
        return userMapper.to(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User#" + id)));
    }

    @Override
    public UserTO findByUsername(String username) {
        return userMapper.to(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User.Username#" + username)));
    }

    @Override
    public UserTO save(NewUserTO newUserTO) {
        User user = userMapper.en(newUserTO);
        user.setId(UUID.randomUUID());
        user.setHash(passwordEncoder.encode(newUserTO.getPassword()));
        return userMapper.to(userRepository.save(user));
    }
}
