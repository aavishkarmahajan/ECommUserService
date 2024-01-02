package com.scaler.ECommUserService.controller;

import com.scaler.ECommUserService.dto.SetUserRolesRequestDTO;
import com.scaler.ECommUserService.dto.UserDTO;
import com.scaler.ECommUserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("id") Long userID){
        UserDTO userDTO = userService.getUserDetails(userID);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("{id}/roles")
    public ResponseEntity<UserDTO> setUserRoles(@PathVariable("id") Long userID, @RequestBody SetUserRolesRequestDTO request){
        UserDTO userDTO = userService.setUserRoles(userID, request.getRoleIDs());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }




}
