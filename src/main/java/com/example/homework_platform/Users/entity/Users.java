package com.example.homework_platform.Users.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String role;
    private Timestamp createdAt;
    private String phoneNumber;
    private String address;
    private String subject;
    private boolean isClassTeacher;
    private String qualification;
    private String classId;

    public void setCreatedAt(java.sql.Timestamp created_at) {
    }
}
