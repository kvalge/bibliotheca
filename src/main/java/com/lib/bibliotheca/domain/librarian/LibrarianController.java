package com.lib.bibliotheca.domain.librarian;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/all")
    @Operation(summary = "Returns all librarians")
    public List<LibrarianResponse> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Deletes librarian by id code")
    public void deleteLibrarian(String idCode){
        librarianService.deleteLibrarian(idCode);
    }
}
