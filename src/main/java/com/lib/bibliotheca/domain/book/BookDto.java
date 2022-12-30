package com.lib.bibliotheca.domain.book;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link Book} entity
 */
@Data
public class BookDto implements Serializable {
    private final Integer id;
    @Size(max = 250)
    @NotNull
    private final String name;
    @NotNull
    private final LocalDate acquisitionDate;
    @NotNull
    private final Integer copyQuantity;
    @NotNull
    private final Integer loanPeriod;
    @Size(max = 50)
    @NotNull
    private final String location;
}