package com.lib.bibliotheca.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Tests whether user saved to database via user service addUser method will not return null if requested by
     * findByUsernameAndPassword method.
     */
    @Test
    void findByUsernameAndPassword() {
        UserRequest userRequest = getUserRequest();

        userService.addUser(userRequest);

        User user = userRepository.findByUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());

        assertNotNull(user);

        userRepository.delete(user);
    }

    /**
     * Tests whether user saved to database via user service addUser method will not return null if requested by
     * findByUserName method.
     */
    @Test
    void findByUserName() {
        UserRequest userRequest = getUserRequest();

        userService.addUser(userRequest);

        User user = userRepository.findByUserName(userRequest.getUsername());

        assertNotNull(user);

        userRepository.delete(user);
    }

    /**
     * Hard coded user request.
     */
    private static UserRequest getUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Kasutaja");
        userRequest.setPassword("Salasõna");
        userRequest.setRoleName("user");
        return userRequest;
    }
}