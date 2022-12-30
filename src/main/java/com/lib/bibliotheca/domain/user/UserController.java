package com.lib.bibliotheca.domain.user;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/new")
    @Operation(summary = "Adds user account")
    public void addUser(@RequestBody UserRequest request) {
        userService.addUser(request);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Deletes user account by username")
    public void deleteUser(@RequestParam String username) {
        userService.deleteUser(username);
    }
}
