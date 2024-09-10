package com.jekirdek.service;

import com.jekirdek.dto.request.UserSaveRequest;
import com.jekirdek.dto.response.UserResponse;
import com.jekirdek.entity.User;
import com.jekirdek.entity.enums.Role;
import com.jekirdek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserResponse registerUser(UserSaveRequest userSaveRequest) {
        User user = new User();
        user.setUsername(userSaveRequest.getUsername());
        user.setEmail(userSaveRequest.getEmail());
        user.setPassword(userSaveRequest.getPassword());
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;
    }
}
