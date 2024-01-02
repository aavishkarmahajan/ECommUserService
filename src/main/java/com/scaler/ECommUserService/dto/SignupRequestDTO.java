package com.scaler.ECommUserService.dto;

import com.scaler.ECommUserService.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SignupRequestDTO {
    private String email;
    private String password;
}
