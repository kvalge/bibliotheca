package com.lib.bibliotheca.domain.lending;

import com.lib.bibliotheca.domain.book.Book;
import com.lib.bibliotheca.domain.librarian.Librarian;
import com.lib.bibliotheca.domain.library_user.LibraryUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "lending")
public class Lending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "lending_date", nullable = false)
    private LocalDate lendingDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "library_user_id", nullable = false)
    private LibraryUser libraryUser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "librarian_id", nullable = false)
    private Librarian librarian;
}