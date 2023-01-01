package com.lib.bibliotheca.validation;

import com.lib.bibliotheca.domain.book.Book;
import com.lib.bibliotheca.domain.book.BookRepository;
import com.lib.bibliotheca.domain.librarian.Librarian;
import com.lib.bibliotheca.domain.librarian.LibrarianRepository;
import com.lib.bibliotheca.domain.library_user.LibraryUser;
import com.lib.bibliotheca.domain.library_user.LibraryUserRepository;
import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import com.lib.bibliotheca.infrastructure.exeption.DataAlreadyExistsException;
import com.lib.bibliotheca.infrastructure.exeption.DataNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ValidationService {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private LibrarianRepository librarianRepository;

    @Resource
    private LibraryUserRepository libraryUserRepository;

    @Resource
    private BookRepository bookRepository;

    /**
     * Checks whether the role already exists in the database.
     */
    public String roleExists(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            return "New role '" + name + "' is added!";
        } else {
            String message = "Role name '" + name + "' already exists";
            throw new DataAlreadyExistsException(message);
        }
    }

    /**
     * Checks whether the chosen username or password already exist in the database.
     */
    public String userExists(String username, String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) || user.getPassword().equals(password)) {
                String message = "Username or password is taken!";
                throw new DataAlreadyExistsException(message);
            }
        }
        return "New user is created!";
    }

    /**
     * Checks whether the librarian with the inserted id code already exist in the database.
     */
    public String librarianExists(String idCode) {
        Librarian librarian = librarianRepository.findByIdCode(idCode);
        if (librarian == null) {
            return "New librarian is added!";
        } else {
            String message = "Librarian with id code '" + idCode + "' already exists";
            throw new DataAlreadyExistsException(message);
        }
    }

    /**
     * Checks whether the library user with inserted id code already exist in the database.
     */
    public String libraryUserExists(String idCode) {
        LibraryUser libraryUser = libraryUserRepository.findByIdCode(idCode);
        if (libraryUser == null) {
            return "New library user is added!";
        } else {
            String message = "Library user with id code '" + idCode + "' already exists";
            throw new DataAlreadyExistsException(message);
        }
    }

    /**
     * Checks whether the book with the inserted name already exist in the database.
     */
    public String bookExists(String name) {
        Book book = bookRepository.findByName(name);
        if (book == null) {
            return "New book is added!";
        } else {
            String message = "Book with name '" + name + "' already exists";
            throw new DataAlreadyExistsException(message);
        }
    }

    /**
     * Checks whether there are roles in the database to return.
     */
    public String rolesNotFound() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.size() != 0) {
            return "Requested list is completed!";
        } else {
            String message = "No roles found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there are librarians in the database to return.
     */
    public String librariansNotFound() {
        List<Librarian> librarianList = librarianRepository.findAll();
        if (librarianList.size() != 0) {
            return "Requested list is completed!";
        } else {
            String message = "No librarian found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there are library users in the database to return.
     */
    public String libraryUsersNotFound() {
        List<LibraryUser> libraryUserList = libraryUserRepository.findAll();
        if (libraryUserList.size() != 0) {
            return "Requested list is completed!";
        } else {
            String message = "No library user found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there are books in the database to return.
     */
    public String booksNotFound() {
        List<Book> bookList = bookRepository.findAll();
        if (bookList.size() != 0) {
            return "Requested list is completed!";
        } else {
            String message = "No book found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is the requested role in the database.
     */
    public String roleNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            return "Role is found!";
        } else {
            String message = "No such role exists!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is the requested librarian in the database.
     */
    public String librarianNotFound(String idCode) {
        Librarian librarian = librarianRepository.findByIdCode(idCode);
        if (librarian != null) {
            return "Librarian is found!";
        } else {
            String message = "No librarian with id code '" + idCode + "' exists!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is the requested library user in the database.
     */
    public String libraryUserNotFound(String idCode) {
        LibraryUser libraryUser = libraryUserRepository.findByIdCode(idCode);
        if (libraryUser != null) {
            return "Library user is found!";
        } else {
            String message = "No library user with id code '" + idCode + "' exists!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is the requested book in the database.
     */
    public String bookNotFound(String name) {
        Book book = bookRepository.findByName(name);
        if (book != null) {
            return "Book is found!";
        } else {
            String message = "No book with the name '" + name + "' exists!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is at least one copy of the requested book in the database.
     */
    public String noCopiesLeft(String bookName) {
        Integer copyQuantity = bookRepository.findByName(bookName).getCopyQuantity();
        if (copyQuantity > 0) {
            return "We have " + copyQuantity + " copies.";
        } else {
            String message = "All copies are lent out at the moment.";
            throw new DataNotFoundException(message);
        }
    }
}
