package com.example.emtechelppathbackend.security.auth.controllers;
import com.example.emtechelppathbackend.security.auth.services.serviceimpl.RoleServiceImpl;
import com.example.emtechelppathbackend.security.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl roleService;

    @PostMapping("/add-new-role")
    public Role addRole(@RequestBody Role role){
        return roleService.addNewRole(role);
    }
}
