package com.lib.bibliotheca.domain.book;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 250)
    @NotNull
    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @NotNull
    @Column(name = "acquisition_date", nullable = false)
    private LocalDate acquisitionDate;

    @NotNull
    @Column(name = "copy_quantity", nullable = false)
    private Integer copyQuantity;

    @NotNull
    @Column(name = "loan_period", nullable = false)
    private Integer loanPeriod;

    @Size(max = 50)
    @NotNull
    @Column(name = "location", nullable = false, length = 50)
    private String location;
}