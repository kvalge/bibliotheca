package com.lib.bibliotheca.domain.book;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("/new")
    @Operation(summary = "Adds a new book")
    public void addNewBook(@RequestBody BookRequest request) {
        bookService.addNewBook(request);
    }

    @GetMapping("/all")
    @Operation(summary = "Returns all books")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/name")
    @Operation(summary = "Returns the book by the name")
    public BookDto getBookByName(String name) {
        return bookService.getBookByName(name);
    }

    @PutMapping("/update")
    @Operation(summary = "Updates the book data")
    public void updateBook(@RequestBody BookRequest request) {
        bookService.updateBook(request);
    }

    @PutMapping("update/name")
    @Operation(summary = "Updates copy quantity by the book name")
    public void updateCopyQuantity(@RequestParam String name, @RequestParam int quantity) {
        bookService.updateCopyQuantity(name, quantity);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Deletes the book by the name")
    public void deleteBook(@RequestParam String name) {
        bookService.deleteBook(name);
    }
}
