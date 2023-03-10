package com.lib.bibliotheca.domain.library_user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
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
    public List<LibraryUserResponse> getAllUsers() {
        return libraryUserService.getAllUsers();
    }

    @GetMapping("/idcode")
    @Operation(summary = "Returns user by id code")
    public LibraryUserResponse getUserByIdCode(@RequestParam String idCode) {
        return libraryUserService.getUserByIdCode(idCode);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Deleting library user by id code also deletes connected user account by user name")
    public void deleteUser(@RequestParam String idCode) {
        libraryUserService.deleteUser(idCode);
    }
}
