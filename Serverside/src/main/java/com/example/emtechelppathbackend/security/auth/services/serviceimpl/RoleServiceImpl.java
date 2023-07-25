package com.example.emtechelppathbackend.security.auth.services.serviceimpl;

import com.example.emtechelppathbackend.security.auth.services.RoleService;
import com.example.emtechelppathbackend.security.entities.Role;
import com.example.emtechelppathbackend.security.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }
}
