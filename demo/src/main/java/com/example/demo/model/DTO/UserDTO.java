package com.example.demo.model.DTO;

import com.example.demo.model.enumerations.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Builder
public class UserDTO {
    @NotBlank(message = "Invalid Username: Empty name ")
    private String userName;
    @NotBlank(message = "Invalid Password: Empty password ")
    private String password;
    boolean active;
    @NotBlank
    private String role;
}
