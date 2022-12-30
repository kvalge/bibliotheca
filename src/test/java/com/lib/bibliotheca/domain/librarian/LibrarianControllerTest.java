package com.lib.bibliotheca.domain.librarian;

import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LibrarianControllerTest {

    @Resource
    private LibrarianController librarianController;

    @Resource
    private LibrarianService librarianService;

    @Resource
    private LibrarianRepository librarianRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    /**
     * Checks whether hard coded librarian request added to database via addLibrarian method will not return
     * null if requested back via repository findByIdCode method
     */
    @Test
    void addLibrarian() {
        Role role = getRole();
        User user = getUser(role);
        LibrarianRequest librarianRequest = getLibrarianRequest();

        librarianController.addLibrarian(librarianRequest);

        Librarian librarian = librarianRepository.findByIdCode(librarianRequest.getIdCode());

        assertNotNull(librarian);

        deleteLibrarian(librarian);
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Tests whether getAllLibrarians method returns the same librarian id code as id code saved to database
     * via librarian service addLibrarian method.
     */
    @Test
    void getAllLibrarians() {
        Role role = getRole();
        User user = getUser(role);
        LibrarianRequest librarianRequest = getLibrarianRequest();

        librarianService.addLibrarian(librarianRequest);

        List<LibrarianDto> librarianDtoList = librarianController.getAllLibrarians();

        String idCode = null;
        String requestIdCode = librarianRequest.getIdCode();
        for (LibrarianDto librarianDto : librarianDtoList) {
            if (requestIdCode.equals(librarianDto.getIdCode())) {
                idCode = librarianDto.getIdCode();
            }
        }

        assertEquals(requestIdCode, idCode);

        deleteLibrarian(librarianRepository.findByIdCode(idCode));
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Tests whether via repository findByIdCode method returns null after using deleteLibrarian method.
     */
    @Test
    void deleteLibrarian() {
        Role role = getRole();
        User user = getUser(role);
        LibrarianRequest librarianRequest = getLibrarianRequest();
        String requestIdCode = librarianRequest.getIdCode();

        librarianService.addLibrarian(librarianRequest);

        librarianController.deleteLibrarian(requestIdCode);

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