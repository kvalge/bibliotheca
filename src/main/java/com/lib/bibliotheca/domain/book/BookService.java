package com.lib.bibliotheca.domain.book;

import com.lib.bibliotheca.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    BookRepository bookRepository;

    @Resource
    private ValidationService validationService;

    /**
     * Validates whether the book already exists in database before adding new book.
     */
    public void addNewBook(BookRequest request) {
        validationService.bookExists(request.getName());

        Book book = bookMapper.toEntity(request);

        Book newBook = new Book();
        newBook.setName(book.getName());
        newBook.setAcquisitionDate(book.getAcquisitionDate());

        int copyQuantity = book.getCopyQuantity();
        newBook.setCopyQuantity(copyQuantity);

        int loanPeriod = 0;
        if (copyQuantity < 5) {
            loanPeriod = 1;
        } else {
            loanPeriod = book.getLoanPeriod();
        }
        newBook.setLoanPeriod(loanPeriod);
        newBook.setLocation(book.getLocation());

        bookRepository.save(newBook);
    }

    /**
     * It is checked isn't the books' database empty before returning all books.
     */
    public List<BookDto> getAllBooks() {
        validationService.booksNotFound();

        List<Book> books = bookRepository.findAll();

        return bookMapper.toDto(books);
    }

    /**
     * It is checked is there a requested book in database before finding book by name.
     */
    public BookDto getBookByName(String name) {
        validationService.bookNotFound(name);

        Book book = bookRepository.findByName(name);

        return bookMapper.toDto(book);
    }

    /**
     * It is checked is there a requested book in database before finding book by name for update.
     * Gives possibility to update values of all book properties in case there have been mistakes in initial
     * data input.
     * Updates loan period according to new copy quantity.
     */
    public void updateBook(BookRequest request) {
        validationService.bookNotFound(request.getName());

        Book book = bookRepository.findByName(request.getName());
        Book updatedBook = bookMapper.partialUpdate(request, book);

        int copyQuantity = updatedBook.getCopyQuantity();
        int loanPeriod = 0;
        if (copyQuantity < 5) {
            loanPeriod = 1;
        } else {
            loanPeriod = updatedBook.getLoanPeriod();
        }
        updatedBook.setLoanPeriod(loanPeriod);

        bookRepository.save(updatedBook);
    }

    /**
     * It is checked is there a requested book in database before finding book by name for updating.
     */
    public void updateCopyQuantity(String name, int quantity) {
        validationService.bookNotFound(name);

        Book book = bookRepository.findByName(name);
        bookMapper.updateQuantity(quantity, book);

        bookRepository.save(book);
    }

    /**
     * It is checked is there a requested book in database before finding book by name for deleting.
     */
    public void deleteBook(String name) {
        validationService.bookNotFound(name);

        bookRepository.deleteByName(name);
    }
}
