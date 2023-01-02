package com.lib.bibliotheca.domain.lending;

import com.lib.bibliotheca.domain.book.Book;
import com.lib.bibliotheca.domain.book.BookRepository;
import com.lib.bibliotheca.domain.book.BookRequest;
import com.lib.bibliotheca.domain.book.BookService;
import com.lib.bibliotheca.domain.librarian.Librarian;
import com.lib.bibliotheca.domain.librarian.LibrarianRepository;
import com.lib.bibliotheca.domain.librarian.LibrarianRequest;
import com.lib.bibliotheca.domain.librarian.LibrarianService;
import com.lib.bibliotheca.domain.library_user.LibraryUser;
import com.lib.bibliotheca.domain.library_user.LibraryUserRepository;
import com.lib.bibliotheca.domain.library_user.LibraryUserRequest;
import com.lib.bibliotheca.domain.library_user.LibraryUserService;
import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import com.lib.bibliotheca.domain.role.RoleRequest;
import com.lib.bibliotheca.domain.role.RoleService;
import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import com.lib.bibliotheca.domain.user.UserRequest;
import com.lib.bibliotheca.domain.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class LendingRepositoryTest {

    @Autowired
    private LendingRepository lendingRepository;

    @Autowired
    private LendingService lendingService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraryUserService libraryUserService;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private LibrarianService librarianService;

    @Autowired
    private LibrarianRepository librarianRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    /**
     * Saves to the database role, user, library user, librarian, book to get data for hard coded lending
     * request saved to the database via lending service addLending method.
     * Tests equality between string of concatenated property values of hard coded request saved to the
     * database via addLending method and property values returned from the database via findByUserIdCodeAndBookName
     * repository method.
      */
    @Test
    void findByUserIdCodeAndBookName() {
        Role role = getRole();
        User user = getUser(role);
        LibraryUser libraryUser = getLibraryUser();
        Librarian librarian = getLibrarian();
        Book book = getBook();

        LendingRequest lendingRequest = getLendingRequest(libraryUser, librarian, book);
        lendingService.addLending(lendingRequest);

        LocalDate lendingDate = lendingRequest.getLendingDate();
        String libraryUserIdCode = libraryUser.getIdCode();
        String bookName = book.getName();
        String librarianIdCode = librarian.getIdCode();
        String requested = lendingDate + libraryUserIdCode + bookName + librarianIdCode;

        List<Lending> lendingList = lendingRepository.findByUserIdCodeAndBookName(libraryUser.getIdCode(), book.getName());
        String returned = null;
        Lending lendingFromRep = null;
        for (Lending lending : lendingList) {
            LocalDate date = lending.getLendingDate();
            String userIdCode = lending.getLibraryUser().getIdCode();
            String name = lending.getBook().getName();
            String libIdCode = lending.getLibrarian().getIdCode();
            returned = date + userIdCode + name + libIdCode;
            lendingFromRep = lending;
        }

        assertEquals(requested, returned);

        deleteLending(lendingFromRep);
        deleteBook(book);
        deleteLibraryUser(libraryUser);
        deleteLibrarian(librarian);
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Saves to the database role, user, library user, librarian, book to get data for hard coded lending
     * request saved to the database via lending service addLending method.
     * Tests whether via findByStatus returned lending and status message doesn't return null;
      */
    @Test
    void findByStatus() {
        Role role = getRole();
        User user = getUser(role);
        LibraryUser libraryUser = getLibraryUser();
        Librarian librarian = getLibrarian();
        Book book = getBook();
        LendingRequest lendingRequest = getLendingRequest(libraryUser, librarian, book);
        lendingService.addLending(lendingRequest);

        String status = null;
        List<Lending> lendingList = lendingRepository.findByUserIdCodeAndBookName(libraryUser.getIdCode(), book.getName());
        for (Lending lending : lendingList) {
            status = lending.getStatus();
        }

        Lending lendingFromRep = null;
        String statusFromRep = null;
        List<Lending> byStatus = lendingRepository.findByStatus(status);
        for (Lending lending : byStatus) {
            lendingFromRep = lending;
            statusFromRep = lending.getStatus();
        }

        assertNotNull(lendingFromRep);
        assertNotNull(statusFromRep);

        deleteLending(lendingFromRep);
        deleteBook(book);
        deleteLibraryUser(libraryUser);
        deleteLibrarian(librarian);
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Hard coded lending request.
     */
    private LendingRequest getLendingRequest(LibraryUser libraryUser, Librarian librarian, Book book) {
        LendingRequest lendingRequest = new LendingRequest();
        lendingRequest.setLendingDate(LocalDate.ofEpochDay(2023 - 1 - 1));
        lendingRequest.setLibraryUserIdCode(libraryUser.getIdCode());
        lendingRequest.setBookName(book.getName());
        lendingRequest.setLibrarianIdCode(librarian.getIdCode());
        return lendingRequest;
    }

    /**
     * Hard coded role request saved to the database, returns role entity from the database.
     */
    private Role getRole() {
        RoleRequest request = new RoleRequest();
        request.setName("Roll");
        roleService.addRole(request);
        return roleRepository.findByName(request.getName());
    }

    /**
     * Hard coded user request saved to the database, returns user from the database.
     */
    private User getUser(Role role) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Kasutaja");
        userRequest.setPassword("Salasõna");
        userRequest.setRoleName(role.getName());
        userService.addUser(userRequest);
        return userRepository.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());
    }

    /**
     * Hard coded library user request saved to database, returns library user from the database.
     */
    private LibraryUser getLibraryUser() {
        LibraryUserRequest libraryUserRequest = new LibraryUserRequest();
        libraryUserRequest.setIdCode("12345678910");
        libraryUserRequest.setFirstName("Eesnimi");
        libraryUserRequest.setLastName("Perekonnanimi");
        libraryUserRequest.setUserName("Kasutaja");
        libraryUserService.addNewUser(libraryUserRequest);
        return libraryUserRepository.findByIdCode(libraryUserRequest.getIdCode());
    }

    /**
     * Hard coded librarian request, returns librarian from the database.
     */
    private Librarian getLibrarian() {
        LibrarianRequest librarianRequest = new LibrarianRequest();
        librarianRequest.setIdCode("12345678910");
        librarianRequest.setUserName("Kasutaja");
        librarianService.addLibrarian(librarianRequest);
        return librarianRepository.findByIdCode(librarianRequest.getIdCode());
    }

    /**
     * Hard coded book request, returns book from the database.
     */
    private Book getBook() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setName("Raamat");
        bookRequest.setAcquisitionDate(LocalDate.ofEpochDay(11 - 12 - 2022));
        bookRequest.setCopyQuantity(3);
        bookRequest.setLoanPeriod(1);
        bookRequest.setLocation("BBBB");
        bookService.addNewBook(bookRequest);
        return bookRepository.findByName(bookRequest.getName());
    }

    private void deleteLending(Lending lending) {
        lendingRepository.delete(lending);
    }

    private void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    private void deleteLibraryUser(LibraryUser libraryUser) {
        libraryUserRepository.delete(libraryUser);
    }

    private void deleteLibrarian(Librarian librarian) {
        librarianRepository.delete(librarian);
    }

    private void deleteUser(User user) {
        userRepository.delete(user);
    }

    private void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}