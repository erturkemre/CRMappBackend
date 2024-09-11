package com.jekirdek.converter;

import com.jekirdek.dto.response.UserResponse;
import com.jekirdek.entity.User;
import com.jekirdek.utils.JwtUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {
    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static List<UserResponse> toResponse(List<User> users) {
        return users.stream().map(UserConverter::toResponse).toList();
    }
}
