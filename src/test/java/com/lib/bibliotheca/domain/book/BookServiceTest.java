package com.lib.bibliotheca.domain.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    BookRepository bookRepository;

    /**
     * Test whether hard coded book request saved to database via addNewBook method doesn't return null
     * requested by book repository findByName method.
     */
    @Test
    void addNewBook() {
        BookRequest bookRequest = getBookRequest();

        bookService.addNewBook(bookRequest);

        Book book = bookRepository.findByName(bookRequest.getName());

        assertNotNull(book);

        deleteBook(book);
    }

    /**
     * Tests equality between property values of hard coded entity saved to database and requested book property
     * values returned via getAllBooks method.
     */
    @Test
    void getAllBooks() {
        Book bookEntity = getBookEntity();
        saveBook(bookEntity);
        String entityProperties = entityProperties(bookEntity);

        List<BookDto> bookDtoList = bookService.getAllBooks();

        String requestedProperties = null;
        for (BookDto bookDto : bookDtoList) {
            if (bookDto.getName().equals(bookEntity.getName())) {
                String dtoName = bookDto.getName();
                LocalDate dtoAcquisitionDate = bookDto.getAcquisitionDate();
                Integer dtoCopyQuantity = bookDto.getCopyQuantity();
                Integer dtoLoanPeriod = bookDto.getLoanPeriod();
                String dtoLocation = bookDto.getLocation();
                requestedProperties = dtoName + dtoAcquisitionDate + dtoCopyQuantity + dtoLoanPeriod + dtoLocation;
            }

        }

        assertEquals(entityProperties, requestedProperties);

        deleteBook(bookEntity);
    }

    /**
     * Tests equality between book entity name saved to database and book name returned via getBookByName method.
     */
    @Test
    void getBookByName() {
        Book bookEntity = getBookEntity();
        saveBook(bookEntity);
        String name = bookEntity.getName();

        String bookName = bookService.getBookByName(name).getName();

        assertEquals(name, bookName);

        deleteBook(bookEntity);
    }

    /**
     * Tests whether book entity properties saved to database and request properties updated in database via
     * updateBook method will not be asserted equals.
     */
    @Test
    void updateBook() {
        Book bookEntity = getBookEntity();
        saveBook(bookEntity);
        String entityProperties = entityProperties(bookEntity);

        BookRequest bookRequest = getBookRequest();

        bookService.updateBook(bookRequest);

        String requestName = bookRequest.getName();
        LocalDate requestAcquisitionDate = bookRequest.getAcquisitionDate();
        Integer requestCopyQuantity = bookRequest.getCopyQuantity();
        Integer requestLoanPeriod = bookRequest.getLoanPeriod();
        String requestLocation = bookRequest.getLocation();
        String requestProperties = requestName + requestAcquisitionDate + requestCopyQuantity + requestLoanPeriod + requestLocation;

        assertNotEquals(entityProperties, requestProperties);

        deleteBook(bookEntity);
    }

    /**
     * Tests whether book entit's' copy quantity saved to database and quantity updated in database via
     * updateCopyQuantity method will not be asserted equals.
     */
    @Test
    void updateCopyQuantity() {
        Book bookEntity = getBookEntity();
        saveBook(bookEntity);
        String name = bookEntity.getName();
        Integer copyQuantity = bookEntity.getCopyQuantity();

        Integer newQuantity = 100;

        bookService.updateCopyQuantity(name, newQuantity);

        assertNotEquals(copyQuantity, newQuantity);

        deleteBook(bookEntity);
    }

    /**
     * Tests whether via repository findByName method returns null after using deleteBook method.
     */
    @Test
    void deleteBook() {
        Book bookEntity = getBookEntity();
        saveBook(bookEntity);
        String name = bookEntity.getName();

        bookService.deleteBook(name);

        Book book = bookRepository.findByName(name);

        assertNull(book);
    }

    /**
     * Hard coded book request.
     */
    private static BookRequest getBookRequest() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setName("Book");
        bookRequest.setAcquisitionDate(LocalDate.ofEpochDay(11 - 12 - 2022));
        bookRequest.setCopyQuantity(3);
        bookRequest.setLoanPeriod(1);
        bookRequest.setLocation("BBBB");
        return bookRequest;
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

    /**
     * Returns entity as sum of its properties.
     */
    private static String entityProperties(Book bookEntity) {
        String name = bookEntity.getName();
        LocalDate acquisitionDate = bookEntity.getAcquisitionDate();
        Integer copyQuantity = bookEntity.getCopyQuantity();
        Integer loanPeriod = bookEntity.getLoanPeriod();
        String location = bookEntity.getLocation();
        return name + acquisitionDate + copyQuantity + loanPeriod + location;
    }

    private void saveBook(Book book) {
        bookRepository.save(book);
    }

    private void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}