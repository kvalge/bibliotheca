package com.lib.bibliotheca.domain.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Tests equality between hard coded role request name and role name saved to database via
     * addRole method.
     */
    @Test
    void addRole() {
        RoleRequest request = new RoleRequest();
        request.setName("Roll");
        String requestName = request.getName();

        roleService.addRole(request);

        Role role = roleRepository.findByName(requestName);
        String name = role.getName();

        assertEquals(requestName, name);

        deleteRole(role);
    }

    /**
     * Tests equality between hard coded role entity name saved to database via repository save method
     * and role name returned via getAllRoles method.
     */
    @Test
    void getAllRoles() {
        Role roleEntity = getRoleEntity();
        saveRole(roleEntity);
        String entityName = roleEntity.getName();

        List<RoleDto> roleDtoList = roleService.getAllRoles();

        String name = null;
        for (RoleDto roleDto : roleDtoList) {
            if (entityName.equals(roleDto.getName())) {
                name = roleDto.getName();
            }
        }

        assertEquals(entityName, name);

        deleteRole(roleEntity);
    }

    /**
     * Tests if role entity deleted by deleteRole method returns null after hard coded entity is
     * saved to repository via repository save method.
     */
    @Test
    void deleteRole() {
        Role roleEntity = getRoleEntity();
        saveRole(roleEntity);
        String entityName = roleEntity.getName();

        roleService.deleteRole(entityName);

        Role role = roleRepository.findByName(entityName);

        assertNull(role);
    }

    /**
     * Hard coded role entity.
     */
    private static Role getRoleEntity() {
        Role roleEntity = new Role();
        roleEntity.setName("Roll");
        return roleEntity;
    }

    private void saveRole(Role role) {
        roleRepository.save(role);
    }

    private void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}