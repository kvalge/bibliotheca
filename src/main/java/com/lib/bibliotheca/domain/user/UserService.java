package com.lib.bibliotheca.domain.user;

import com.lib.bibliotheca.domain.role.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource UserRepository userRepository;

    public void addUser(UserRequest request) {
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
