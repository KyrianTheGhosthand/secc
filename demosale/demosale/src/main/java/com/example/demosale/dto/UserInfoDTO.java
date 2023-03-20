package com.example.demosale.dto;

import com.example.demosale.entity.Role;
import com.example.demosale.entity.User;
import lombok.Getter;

@Getter
public class UserInfoDTO {
    private final long id;
    private final String username;
    private final String fullName;
    private final Role role;

    public UserInfoDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        fullName = user.getFullname();
        role = user.getRole();
    }
}
