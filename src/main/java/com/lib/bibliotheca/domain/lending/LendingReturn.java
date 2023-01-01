package com.lib.bibliotheca.domain.lending;

import lombok.Data;

import java.io.Serializable;

@Data
public class LendingReturn implements Serializable {

    private String libraryUserFirstName;
    private String libraryUserLastName;
    private String bookName;
    private long daysOverdue;
}
