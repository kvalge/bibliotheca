package com.lib.bibliotheca.domain.user;

import com.lib.bibliotheca.domain.role.Role;
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
    private ValidationService validationService;

    public void addUser(UserRequest request) {
        validationService.userExists(request.getUsername(), request.getPassword());

        User user = userMapper.toEntity(request);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());

        Integer id = user.getRole().getId();
        String name = user.getRole().getName();
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        newUser.setRole(role);

        userRepository.save(newUser);
    }
}
