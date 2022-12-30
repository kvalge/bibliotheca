package com.lib.bibliotheca.domain.library_user;

import lombok.Data;

import java.io.Serializable;

@Data
public class LibraryUserResponse implements Serializable {
    private String idCode;
    private String firstName;
    private String lastName;
}
