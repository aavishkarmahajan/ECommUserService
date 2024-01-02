package com.scaler.ECommUserService.service;

import com.scaler.ECommUserService.dto.UserDTO;
import com.scaler.ECommUserService.mapper.UserMapper;
import com.scaler.ECommUserService.model.Role;
import com.scaler.ECommUserService.repository.RoleRepository;
import com.scaler.ECommUserService.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.scaler.ECommUserService.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserDetails(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        return UserMapper.userToUserDTO(user.get());
    }

    public UserDTO setUserRoles(Long userID, List<Long> roleIDs){
        Optional<User> userOptional = userRepository.findById(userID);
        Set<Role> roles = roleRepository.findAllByIdIn(roleIDs);

        if(userOptional.isEmpty()){
            return null;
        }
        User user = userOptional.get();
        user.setRoles(roles);
        User savedUser = userRepository.save(user);
        return UserMapper.userToUserDTO(savedUser);
    }
}
