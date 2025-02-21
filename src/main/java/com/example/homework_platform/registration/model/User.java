package com.example.homework_platform.registration.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String passwordHash;
    private String role;
    private LocalDateTime createdAt;
    private String phoneNumber;
    private String address;
    private String subject;
    private Boolean isClassTeacher;
    private String qualification;
    private String className;

}

