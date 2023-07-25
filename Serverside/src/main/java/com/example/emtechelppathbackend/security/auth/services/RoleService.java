package com.example.emtechelppathbackend.security.auth.services;
import com.example.emtechelppathbackend.security.entities.Role;
import org.springframework.stereotype.Service;

@Service

public interface RoleService {
   public Role addNewRole(Role role);
}