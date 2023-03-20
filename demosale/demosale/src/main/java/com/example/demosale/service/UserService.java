package com.example.demosale.service;

import com.example.demosale.dto.LoginRequestDTO;
import com.example.demosale.dto.LoginResponseDTO;
import com.example.demosale.dto.UserInfoDTO;

public interface UserService {
    LoginResponseDTO login(LoginRequestDTO dto);

    String getCurrentUsername();

    UserInfoDTO getUserInfo(String username);

    Long countUsers();
}
