package io.may4th.chat.security;

import io.may4th.chat.services.UserService;
import io.may4th.chat.services.tos.UserTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserPrincipalService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserTO userTO = userService.findByUsername(username);
        return new UserPrincipal(userTO.getId(), userTO.getUsername(), userTO.getHash());
    }

    UserDetails loadUserById(UUID id) {
        UserTO userTO = userService.findById(id);
        return new UserPrincipal(userTO.getId(), userTO.getUsername(), userTO.getHash());
    }
}
