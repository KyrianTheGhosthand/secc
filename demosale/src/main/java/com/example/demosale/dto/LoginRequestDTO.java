package com.example.demosale.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginRequestDTO {
    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

}
