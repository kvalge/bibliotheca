package com.lib.bibliotheca.domain.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Tests if role entity found by findByName method doesn't return null after hard coded entity is
     * saved to repository via repository save method.
     */
    @Test
    void findByName() {
        Role roleEntity = getRoleEntity();
        saveRole(roleEntity);
        String roleName = roleEntity.getName();

        Role role = roleRepository.findByName(roleName);

        assertNotNull(role);

        deleteRole(roleEntity);
    }

    /**
     * Tests if role entity deleted by deleteByName method returns null after hard coded entity is
     * saved to repository via repository save method.
     */
    @Test
    void deleteByName() {
        Role roleEntity = getRoleEntity();
        saveRole(roleEntity);
        String roleName = roleEntity.getName();

        roleRepository.deleteByName(roleName);

        Role role = roleRepository.findByName(roleName);

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