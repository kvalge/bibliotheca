package com.lib.bibliotheca.domain.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link Role} entity
 */
@Data
public class RoleDto implements Serializable {
    private final Integer id;
    @Size(max = 25)
    @NotNull
    private final String name;
}