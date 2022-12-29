package com.lib.bibliotheca.domain.role;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRepository roleRepository;

    public void addRole(RoleRequest request) {
        Role role = roleMapper.toEntity(request);

        Role newRole = new Role();
        newRole.setName(role.getName());
        roleRepository.save(newRole);
    }
}
