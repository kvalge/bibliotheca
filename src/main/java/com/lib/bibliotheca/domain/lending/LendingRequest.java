package com.lib.bibliotheca.domain.lending;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class LendingRequest implements Serializable {

    private LocalDate lendingDate;
    private String libraryUserIdCode;
    private String bookName;
    private String librarianIdCode;
}
