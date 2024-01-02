package com.scaler.ECommUserService.mapper;

import com.scaler.ECommUserService.dto.UserDTO;
import com.scaler.ECommUserService.model.Role;
import com.scaler.ECommUserService.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserMapper {
    private String email;
    private Set<Role> roleSet = new HashSet<>();

    public static UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleSet(user.getRoles());
        return userDTO;
    }
}
