package com.scaler.ECommUserService.dto;

import com.scaler.ECommUserService.model.Role;
import com.scaler.ECommUserService.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String email;
    private Set<Role> roleSet = new HashSet<>();
}
