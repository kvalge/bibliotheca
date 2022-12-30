package com.lib.bibliotheca.domain.library_user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/libraryuser")
public class LibraryUserController {

    @Resource
    private LibraryUserService libraryUserService;

    @PostMapping("/new")
    @Operation(summary = "Adds new library user")
    public void addNewUser(@RequestBody LibraryUserRequest request) {
        libraryUserService.addNewUser(request);
    }

    @GetMapping("/all")
    @Operation(summary = "Returns all library users")
    public List<LibraryUserDto> getAllUsers() {
        return libraryUserService.getAllUsers();
    }

}
