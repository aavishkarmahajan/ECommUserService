package com.scaler.ECommUserService.service;

import com.scaler.ECommUserService.model.Role;
import com.scaler.ECommUserService.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    public Role createRole(String name){
        Role role = new Role();
        role.setRole(name);
        return roleRepository.save(role);
    }
}
