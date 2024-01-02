package com.scaler.ECommUserService.controller;

import com.scaler.ECommUserService.dto.LoginRequestDTO;
import com.scaler.ECommUserService.dto.SignupRequestDTO;
import com.scaler.ECommUserService.dto.UserDTO;
import com.scaler.ECommUserService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupRequestDTO signupRequestDTO){
        UserDTO userDTO = authService.signup(signupRequestDTO.getEmail(), signupRequestDTO.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        //return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


}
