package com.lib.bibliotheca.domain.book;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/all")
    @Operation(summary = "Returns all books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/name")
    @Operation(summary = "Returns book by name")
    public BookDto getBookByName(String name) {
        return bookService.getBookByName(name);
    }

    @PutMapping("/update")
    @Operation(summary = "Updates the book data")
    public void updateBook(@RequestBody BookRequest request) {
        bookService.updateBook(request);
    }
}
