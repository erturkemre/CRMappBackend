package com.jekirdek.dto.request;

import java.time.LocalDateTime;

public class CuustomerRequest {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String region;
    private LocalDateTime registrationDate;

    private Long userId;
}
