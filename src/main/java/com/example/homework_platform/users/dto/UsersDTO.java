package com.example.homework_platform.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UsersDTO {

    private int id;

    @NotEmpty(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotEmpty(message = "First name is required")
    @Size(max = 50, message = "First name can have a maximum of 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(max = 50, message = "Last name can have a maximum of 50 characters")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Size(max = 100)
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String passwordHash;

    @NotNull(message = "Role is required")
    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    private String phoneNumber;

    private String address;

    private String subject;

    private boolean isClassTeacher;

    private String qualification;

    private String classId;
}
