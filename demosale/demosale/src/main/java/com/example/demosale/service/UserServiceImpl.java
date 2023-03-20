package com.example.demosale.service;

import com.example.demosale.dto.LoginRequestDTO;
import com.example.demosale.dto.LoginResponseDTO;
import com.example.demosale.dto.UserInfoDTO;
import com.example.demosale.entity.User;
import com.example.demosale.exception.ApiException;
import com.example.demosale.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto){
        Authentication auth;
        try{
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
        }catch (AuthenticationException ex){
            throw new ApiException(401, "Invalid username or password");
        }
        String accessToken = jwtService.generateJwt(auth);
        return new LoginResponseDTO(accessToken);
    }

    @Override
    public String getCurrentUsername(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            throw new ApiException(401, "Login Required");
        }
        return auth.getName();
    }
    @Override
    public UserInfoDTO getUserInfo(String username){
        User user = userRepository.findbyUsername(username)
                .orElseThrow(() -> new ApiException(404, "User not found"));
        return new UserInfoDTO(user);
    }
    @Override
    public Long countUsers() {return userRepository.count();}
}
