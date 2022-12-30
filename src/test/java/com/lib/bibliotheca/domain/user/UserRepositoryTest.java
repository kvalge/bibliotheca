package com.lib.bibliotheca.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Resource
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