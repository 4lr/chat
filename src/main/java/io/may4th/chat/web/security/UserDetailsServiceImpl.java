package io.may4th.chat.web.security;

import io.may4th.chat.security.api.UserDetailsService;
import io.may4th.chat.services.UserService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) {
        val userTO = userService.findByUsername(username);
        return new UserDetailsImpl(userTO.getId(), userTO.getUsername(), userTO.getHash());
    }

    @Override
    public UserDetailsImpl loadUserById(String id) {
        val userTO = userService.findById(UUID.fromString(id));
        return new UserDetailsImpl(userTO.getId(), userTO.getUsername(), userTO.getHash());
    }
}
