package com.jekirdek.controller;

import com.jekirdek.dto.request.UserLoginRequest;
import com.jekirdek.dto.request.UserSaveRequest;
import com.jekirdek.dto.response.UserResponse;
import com.jekirdek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserSaveRequest userSaveRequest){
        UserResponse createdUser = userService.registerUser(userSaveRequest);
        return createdUser;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest userLoginRequest){
        return ResponseEntity.ok(userService.loginUser(userLoginRequest));
    }


    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserSaveRequest userSaveRequest) {
        UserResponse updatedUser = userService.updateUser(id, userSaveRequest);
        return updatedUser;
    }

}
