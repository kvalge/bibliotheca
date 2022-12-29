package com.lib.bibliotheca.domain.role;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/new")
    @Operation(summary = "Adds role")
    public void addRole(@RequestBody RoleRequest request) {
        roleService.addRole(request);
    }

    @GetMapping("/all")
    @Operation(summary = "Returns all roles")
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete role by role name")
    public void deleteRole(String name) {
        roleService.deleteRole(name);
    }
}
