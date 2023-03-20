package com.example.demosale.controller;

import com.example.demosale.dto.LoginRequestDTO;
import com.example.demosale.dto.LoginResponseDTO;
import com.example.demosale.dto.UserInfoDTO;
import com.example.demosale.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto){return userService.login(dto);}

    @GetMapping("/current")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public UserInfoDTO getCurrentUser() {
        var currentUsername = userService.getCurrentUsername();
        return userService.getUserInfo(currentUsername);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long countUsers() {
        return userService.countUsers();
    }


}
