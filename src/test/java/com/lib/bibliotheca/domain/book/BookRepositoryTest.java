package com.lib.bibliotheca.domain.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Tests whether findByName method will not return null if requested entity has been saved to database.
     */
    @Test
    void findByName() {
        Book bookEntity = getBookEntity();
        saveBook(bookEntity);
        String name = bookEntity.getName();

        Book book = bookRepository.findByName(name);

        assertNotNull(book);

        deleteBook(bookEntity);
    }

    /**
     * Tests whether via repository findByName method returns null after using deleteBook method.
     */
    @Test
    void deleteByName() {
        Book bookEntity = getBookEntity();
        saveBook(bookEntity);
        String name = bookEntity.getName();

        bookRepository.deleteByName(name);

        Book book = bookRepository.findByName(name);

        assertNull(book);
    }

    /**
     * Hard coded book entity.
     */
    private static Book getBookEntity() {
        Book bookEntity = new Book();
        bookEntity.setName("Book");
        bookEntity.setAcquisitionDate(LocalDate.ofEpochDay(12 - 12 - 2022));
        bookEntity.setCopyQuantity(5);
        bookEntity.setLoanPeriod(4);
        bookEntity.setLocation("AAAA");
        return bookEntity;
    }

    private void saveBook(Book book) {
        bookRepository.save(book);
    }

    private void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}