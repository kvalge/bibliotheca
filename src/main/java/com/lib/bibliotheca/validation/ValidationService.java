package com.lib.bibliotheca.validation;

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

    /**
     * Checks whether role already exists in database.
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
     * Checks whether chosen username or password already exist in database.
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
     * Checks whether librarian with inserted id code already exist in database.
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
     * Checks whether library user with inserted id code already exist in database.
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
     * Checks whether there are roles in database to return.
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
     * Checks whether there are librarians in database to return.
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
     * Checks whether there are library users in database to return.
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
     * Checks whether there is requested role in database.
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
     * Checks whether there is requested librarian in database.
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
     * Checks whether there is requested library user in database.
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
}
