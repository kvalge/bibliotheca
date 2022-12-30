package com.lib.bibliotheca.domain.librarian;

import lombok.Data;

import java.io.Serializable;

@Data
public class LibrarianRequest implements Serializable {
    private String idCode;
    private String userName;
}
