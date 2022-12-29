package com.lib.bibliotheca.domain.user;

import com.lib.bibliotheca.domain.role.RoleDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Integer id;
    @Size(max = 50)
    @NotNull
    private final String username;
    @Size(max = 200)
    @NotNull
    private final String password;
    @NotNull
    private final RoleDto role;
}