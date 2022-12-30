package com.lib.bibliotheca.domain.library_user;

import com.lib.bibliotheca.domain.librarian.LibrarianRepository;
import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class LibraryUserRepositoryTest {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private LibraryUserService libraryUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LibrarianRepository librarianRepository;

    /**
     * Tests equality between hard coded library user request id code saved to database via library user service
     * addNewUser method and id code returned via findByIdCode method.
     */
    @Test
    void findByIdCode() {
        Role role = getRole();
        User user = getUser(role);
        LibraryUserRequest libraryUserRequest = getLibraryUserRequest();
        String requestIdCode = libraryUserRequest.getIdCode();

        libraryUserService.addNewUser(libraryUserRequest);

        LibraryUser libraryUser = libraryUserRepository.findByIdCode(requestIdCode);

        assertEquals(requestIdCode, libraryUser.getIdCode());

        deleteLibraryUser(libraryUser);
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
        LibraryUserRequest libraryUserRequest = getLibraryUserRequest();
        String requestIdCode = libraryUserRequest.getIdCode();

        libraryUserService.addNewUser(libraryUserRequest);

        libraryUserRepository.deleteByIdCode(requestIdCode);

        LibraryUser libraryUser = libraryUserRepository.findByIdCode(requestIdCode);

        assertNull(libraryUser);

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
     * Hard coded library user request saved to database.
     */
    private static LibraryUserRequest getLibraryUserRequest() {
        LibraryUserRequest libraryUserRequest = new LibraryUserRequest();
        libraryUserRequest.setIdCode("12345678910");
        libraryUserRequest.setFirstName("Eesnimi");
        libraryUserRequest.setLastName("Perekonnanimi");
        libraryUserRequest.setUserName("Kasutaja");
        return libraryUserRequest;
    }

    private void deleteLibraryUser(LibraryUser libraryUser) {
        libraryUserRepository.delete(libraryUser);
    }

    private void deleteUser(User user) {
        userRepository.delete(user);
    }

    private void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}