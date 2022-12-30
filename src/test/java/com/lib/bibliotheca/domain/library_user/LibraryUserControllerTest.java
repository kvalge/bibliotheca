package com.lib.bibliotheca.domain.library_user;

import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LibraryUserControllerTest {

    @Autowired
    private LibraryUserController libraryUserController;

    @Autowired
    private LibraryUserService libraryUserService;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Checks whether hard coded library user request added to database via addNewUser method will not
     * return null if requested back via repository findByIdCode method
     */
    @Test
    void addNewUser() {
        Role role = getRole();
        User user = getUser(role);
        LibraryUserRequest libraryUserRequest = getLibraryUserRequest();

        libraryUserController.addNewUser(libraryUserRequest);

        LibraryUser libraryUser = libraryUserRepository.findByIdCode(libraryUserRequest.getIdCode());

        assertNotNull(libraryUser);

        deleteLibraryUser(libraryUser);
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Tests whether getAllUsers method returns the same library user id code as id code saved to database
     * via library user service addNewUser method.
     */
    @Test
    void getAllUsers() {
        Role role = getRole();
        User user = getUser(role);
        LibraryUserRequest libraryUserRequest = getLibraryUserRequest();

        libraryUserService.addNewUser(libraryUserRequest);

        List<LibraryUserResponse> responseList = libraryUserController.getAllUsers();

        String idCode = null;
        String requestIdCode = libraryUserRequest.getIdCode();
        for (LibraryUserResponse libraryUserResponse : responseList) {
            if (requestIdCode.equals(libraryUserResponse.getIdCode())) {
                idCode = libraryUserResponse.getIdCode();
            }
        }

        assertEquals(requestIdCode, idCode);

        deleteLibraryUser(libraryUserRepository.findByIdCode(idCode));
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Tests equality between hard coded library user request properties and response properties saved to
     * database via libraryUserService addNewUser method and returned via getUserByIdCode method.
     */
    @Test
    void getUserByIdCode() {
        Role role = getRole();
        User user = getUser(role);
        LibraryUserRequest libraryUserRequest = getLibraryUserRequest();
        String requestIdCode = libraryUserRequest.getIdCode();
        String requestFirstName = libraryUserRequest.getFirstName();
        String requestLastName = libraryUserRequest.getLastName();
        String request = requestIdCode + requestFirstName + requestLastName;

        libraryUserService.addNewUser(libraryUserRequest);

        LibraryUserResponse libraryUserResponse = libraryUserController.getUserByIdCode(requestIdCode);
        String idCode = libraryUserResponse.getIdCode();
        String firstName = libraryUserResponse.getFirstName();
        String lastName = libraryUserResponse.getLastName();
        String response = idCode + firstName + lastName;

        assertEquals(request, response);

        deleteLibraryUser(libraryUserRepository.findByIdCode(requestIdCode));
        deleteUser(user);
        deleteRole(role);
    }

    /**
     * Tests whether via repository findByIdCode method returns null after using deleteUser method.
     */
    @Test
    void deleteUser() {
        Role role = getRole();
        User user = getUser(role);
        LibraryUserRequest libraryUserRequest = getLibraryUserRequest();
        String requestIdCode = libraryUserRequest.getIdCode();

        libraryUserService.addNewUser(libraryUserRequest);

        libraryUserController.deleteUser(requestIdCode);

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