package com.example.homework_platform.registration.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class RegisterRequest {
    @NotBlank(message = "Email cannot be empty")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format"
    )
    public String email;

    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public String role;
    public String phoneNumber;
    public String address;
    public String subject;
    public Boolean isClassTeacher;
    public String qualification;
    public String className;
}

