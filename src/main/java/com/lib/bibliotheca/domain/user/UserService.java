package com.lib.bibliotheca.domain.user;

import com.lib.bibliotheca.domain.role.Role;
import com.lib.bibliotheca.domain.role.RoleRepository;
import com.lib.bibliotheca.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private ValidationService validationService;

    /**
     * Validates whether the username or password already exists in database before adding new user.
     */
    public void addUser(UserRequest request) {
        validationService.userExists(request.getUsername(), request.getPassword());

        User user = userMapper.toEntity(request);

        Role role = roleRepository.findByName(request.getRoleName());

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRole(role);

        userRepository.save(newUser);
    }
}
