package com.lib.bibliotheca.domain.lending;

import com.lib.bibliotheca.domain.book.BookDto;
import com.lib.bibliotheca.domain.librarian.LibrarianDto;
import com.lib.bibliotheca.domain.library_user.LibraryUserDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link Lending} entity
 */
@Data
public class LendingDto implements Serializable {
    private final Integer id;
    @NotNull
    private final LocalDate lendingDate;
    private final LocalDate dueDate;
    private final LocalDate returnDate;
    @Size(max = 50)
    private final String status;
    @NotNull
    private final LibraryUserDto libraryUser;
    @NotNull
    private final BookDto book;
    @NotNull
    private final LibrarianDto librarian;
}