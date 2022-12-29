package com.lib.bibliotheca.domain.role;

import com.lib.bibliotheca.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private ValidationService validationService;

    /**
     * It is checked whether the role already exists in database before adding new role.
     */
    public void addRole(RoleRequest request) {
        validationService.roleExists(request.getName());

        Role role = roleMapper.toEntity(request);

        Role newRole = new Role();
        newRole.setName(role.getName());
        roleRepository.save(newRole);
    }

    /**
     * It is checked isn't the role database empty before returning all roles.
     */
    public List<RoleDto> getAllRoles() {
        validationService.rolesNotFound();

        List<Role> roles = roleRepository.findAll();

        return roleMapper.toDto(roles);
    }

    /**
     * It is checked is there a requested role in database before finding role by name for deleting.
     */
    public void deleteRole(String name) {
        validationService.roleNotFound(name);

        roleRepository.deleteByName(name);
    }
}
