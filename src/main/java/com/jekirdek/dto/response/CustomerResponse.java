package com.jekirdek.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String region;
    private LocalDateTime registrationDate;
    private Long userId;
}
