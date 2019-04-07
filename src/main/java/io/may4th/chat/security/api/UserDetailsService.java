package io.may4th.chat.security.api;

public interface UserDetailsService {

    UserDetails loadUserByUsername(String username);

    UserDetails loadUserById(String id);
}
