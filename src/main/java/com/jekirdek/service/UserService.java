package com.jekirdek.service;

import com.jekirdek.converter.UserConverter;
import com.jekirdek.dto.request.UserSaveRequest;
import com.jekirdek.dto.response.UserResponse;
import com.jekirdek.entity.User;
import com.jekirdek.entity.enums.Role;
import com.jekirdek.exception.ErrorException;
import com.jekirdek.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public UserResponse registerUser(UserSaveRequest userSaveRequest) {
        User user = new User();
        user.setUsername(userSaveRequest.getUsername());
        user.setEmail(userSaveRequest.getEmail());
        user.setPassword(userSaveRequest.getPassword());
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
            user.setPassword(userSaveRequest.getPassword());
        }


        User updatedUser = userRepository.save(user);
        return UserConverter.toResponse(updatedUser);
    }
}
