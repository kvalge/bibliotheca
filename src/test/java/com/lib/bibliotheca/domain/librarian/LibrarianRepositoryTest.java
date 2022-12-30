package com.lib.bibliotheca.domain.librarian;

import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class LibrarianRepositoryTest {

    @Resource
    private LibrarianRepository librarianRepository;

    @Resource
    private LibrarianService librarianService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    /**
     * Tests equality between hard coded librarian request id code saved to database and id code returned
     * via findByIdCode method.
     */
    @Test
    void findByIdCode() {
        Role role = getRole();
        User user = getUser(role);
        LibrarianRequest librarianRequest = getLibrarianRequest();
        String requestIdCode = librarianRequest.getIdCode();

        librarianService.addLibrarian(librarianRequest);

        Librarian librarian = librarianRepository.findByIdCode(requestIdCode);

        assertEquals(requestIdCode, librarian.getIdCode());

        deleteLibrarian(librarian);
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Tests whether via repository findByIdCode method returns null after using deleteByIdCode method.
     */
    @Test
    void deleteByIdCode() {
        Role role = getRole();
        User user = getUser(role);
        LibrarianRequest librarianRequest = getLibrarianRequest();
        String requestIdCode = librarianRequest.getIdCode();

        librarianService.addLibrarian(librarianRequest);

        librarianRepository.deleteByIdCode(requestIdCode);

        Librarian librarian = librarianRepository.findByIdCode(requestIdCode);

        assertNull(librarian);

        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Hard coded role entity saved to database.
     */
    private Role getRole() {
        Role role = new Role();
        role.setName("Roll");
        roleRepository.save(role);
        return role;
    }

    /**
     * Hard coded user entity saved to database.
     */
    private User getUser(Role role) {
        User user = new User();
        user.setUsername("Kasutaja");
        user.setPassword("Salasõna");
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    /**
     * Hard coded librarian request.
     */
    private static LibrarianRequest getLibrarianRequest() {
        LibrarianRequest librarianRequest = new LibrarianRequest();
        librarianRequest.setIdCode("12345678910");
        librarianRequest.setUserName("Kasutaja");
        return librarianRequest;
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