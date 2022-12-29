package com.lib.bibliotheca.validation;

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
     * Checks whether there are roles in database to return.
     */
    public String rolesNotFound() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.size() != 0) {
            return "Role list request is completed!";
        } else {
            String message = "No roles found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is certain role in database.
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
}
