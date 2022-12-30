package com.lib.bibliotheca.domain.librarian;

import com.lib.bibliotheca.domain.user.UserDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Librarian} entity
 */
@Data
public class LibrarianDto implements Serializable {
    private final Integer id;
    @Size(max = 11)
    @NotNull
    private final String idCode;
    @NotNull
    private final UserDto user;
}