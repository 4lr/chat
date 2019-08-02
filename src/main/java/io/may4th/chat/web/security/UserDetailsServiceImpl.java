package io.may4th.chat.web.security;

import io.may4th.chat.security.api.UserDetailsService;
import io.may4th.chat.services.api.UserService;
import io.may4th.chat.services.api.tos.UserTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TreeSet;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserService userService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) {
        return userDetails(userService.findByUsername(username));
    }

    @Override
    public UserDetailsImpl loadUserById(String id) {
        return userDetails(userService.findById(UUID.fromString(id)));
    }

    private UserDetailsImpl userDetails(UserTO userTO) {
        return new UserDetailsImpl(userTO.getId(), userTO.getUsername(), userTO.getHash(), new TreeSet<>(userTO.getRooms()));
    }
}
