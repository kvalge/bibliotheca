package com.lib.bibliotheca.domain.librarian;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

    @Resource
    private LibrarianService librarianService;

    @PostMapping("/new")
    @Operation(summary = "Adds new librarian")
    public void addLibrarian(@RequestBody LibrarianRequest request) {
        librarianService.addLibrarian(request);
    }
}
