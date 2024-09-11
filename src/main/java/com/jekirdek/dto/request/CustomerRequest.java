package com.jekirdek.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String region;
    private LocalDateTime registrationDate;
    private Long userId;
}
