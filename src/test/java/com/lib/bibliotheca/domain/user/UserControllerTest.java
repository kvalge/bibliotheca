package com.lib.bibliotheca.domain.user;

import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Tests whether hard coded user request saved to database via addUser method will not return null.
     */
    @Test
    void addUser() {
        Role role = new Role();
        role.setName("user");
        roleRepository.save(role);

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Kasutaja");
        userRequest.setPassword("Salasõna");
        userRequest.setRoleName(role.getName());

        userController.addUser(userRequest);

        User user = userRepository.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());

        assertNotNull(user);

        userRepository.delete(user);
        roleRepository.delete(role);
    }
}