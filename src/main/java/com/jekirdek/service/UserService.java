package com.jekirdek.service;

import com.jekirdek.converter.UserConverter;
import com.jekirdek.dto.request.UserLoginRequest;
import com.jekirdek.dto.request.UserSaveRequest;
import com.jekirdek.dto.response.UserResponse;
import com.jekirdek.entity.User;
import com.jekirdek.entity.enums.Role;
import com.jekirdek.exception.ErrorException;
import com.jekirdek.repository.UserRepository;
import com.jekirdek.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final Environment env;


    public String checkUser(){
        List<User> adminList = userRepository.findAdmins();
        if(!adminList.isEmpty()){
            return "admin already exist";
        }
        User user = new User();
        user.setUsername("admin");
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(env.getProperty("ADMIN_PASSWORD")));
        user.setEmail(env.getProperty("ADMIN_EMAIL"));
        userRepository.save(user);
        return "admin created";
    }
    public UserResponse registerUser(UserSaveRequest userSaveRequest) {
        User user = new User();
        user.setUsername(userSaveRequest.getUsername());
        user.setEmail(userSaveRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        return UserConverter.toResponse(savedUser);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserSaveRequest userSaveRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ErrorException("User not found"));

        user.setUsername(userSaveRequest.getUsername());
        if (!userSaveRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
        }


        User updatedUser = userRepository.save(user);
        return UserConverter.toResponse(updatedUser);
    }

    public UserResponse loginUser(UserLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ErrorException("USER_NOT_FOUND"));

        String token = jwtUtil.generateToken(user,user.getId(),String.valueOf(user.getRole()),user.getEmail());
        UserResponse userResponse = UserConverter.toResponse(user);
        userResponse.setToken(token);

        return userResponse;
    }

}
