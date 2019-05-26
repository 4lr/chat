package io.may4th.chat.web.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.may4th.chat.security.api.GrantedAuthority;
import io.may4th.chat.security.api.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
public class UserDetailsImpl implements UserDetails {

    UserDetailsImpl(UUID id, String username, String password) {
        this.id = id.toString();
        this.username = username;
        this.password = password;
    }

    private final String id;

    private final String username;

    @JsonIgnore
    private final String password;

    private final Collection<GrantedAuthorityImpl> authorities = Collections.singletonList(new GrantedAuthorityImpl("ROLE_USER"));

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @AllArgsConstructor
    @Getter
    private static class GrantedAuthorityImpl implements GrantedAuthority {

        private final String authority;
    }
}
