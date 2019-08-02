package io.may4th.chat.web.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.may4th.chat.security.api.GrantedAuthority;
import io.may4th.chat.security.api.UserDetails;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@ApiModel("UserDetails")
@Getter
public class UserDetailsImpl implements UserDetails {

    @ApiModelProperty(required = true, example = "00000000-0000-0000-C000-000000000246")
    private final String id;

    @JsonIgnore
    private final UUID uuid;

    @ApiModelProperty(required = true, example = "John")
    private final String username;

    @JsonIgnore
    private final String password;

    @ApiModelProperty(required = true)
    private final Collection<GrantedAuthorityImpl> authorities = Collections.singletonList(new GrantedAuthorityImpl("ROLE_USER"));

    @ApiModelProperty(required = true)
    private final Set<UUID> rooms;

    @ApiModelProperty(required = true)
    private final boolean accountNonExpired = true;

    @ApiModelProperty(required = true)
    private final boolean accountNonLocked = true;

    @ApiModelProperty(required = true)
    private final boolean credentialsNonExpired = true;

    @ApiModelProperty(required = true)
    private final boolean enabled = true;

    UserDetailsImpl(UUID id, String username, String password, Set<UUID> rooms) {
        this.id = id.toString();
        this.uuid = id;
        this.username = username;
        this.password = password;
        this.rooms = rooms;
    }

    @ApiModel("GrantedAuthority")
    @AllArgsConstructor
    @Getter
    private static class GrantedAuthorityImpl implements GrantedAuthority {

        @ApiModelProperty(required = true, example = "ROLE_USER")
        private final String authority;
    }
}
