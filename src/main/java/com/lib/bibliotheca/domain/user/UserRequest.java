package com.lib.bibliotheca.domain.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {

    private String username;
    private String password;
    private String roleName;
}
