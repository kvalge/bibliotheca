package com.lib.bibliotheca.domain.role;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
