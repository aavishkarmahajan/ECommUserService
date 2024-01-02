package com.scaler.ECommUserService.controller;

import com.scaler.ECommUserService.dto.CreateRoleRequestDTO;
import com.scaler.ECommUserService.model.Role;
import com.scaler.ECommUserService.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(CreateRoleRequestDTO request){
        Role role = roleService.createRole(request.getRoleName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
