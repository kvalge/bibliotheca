package com.lib.bibliotheca.domain.book;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("/new")
    @Operation(summary = "Adds new book")
    public void addNewBook(@RequestBody BookRequest request) {
        bookService.addNewBook(request);
    }
}
