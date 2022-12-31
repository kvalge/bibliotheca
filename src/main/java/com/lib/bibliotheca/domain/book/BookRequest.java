package com.lib.bibliotheca.domain.book;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BookRequest implements Serializable {

    private String name;
    private LocalDate acquisitionDate;
    private Integer copyQuantity;
    private Integer loanPeriod;
    private String location;
}
