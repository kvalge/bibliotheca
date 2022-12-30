package com.lib.bibliotheca.domain.library_user;

import com.lib.bibliotheca.domain.user.UserDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link LibraryUser} entity
 */
@Data
public class LibraryUserDto implements Serializable {
    private final Integer id;
    @Size(max = 11)
    @NotNull
    private final String idCode;
    @Size(max = 50)
    @NotNull
    private final String firstName;
    @Size(max = 50)
    @NotNull
    private final String lastName;
    @NotNull
    private final UserDto user;
}