package com.example.homework_platform.login.dto;

public class LoginRequest {
    private String identifier;
    private String password;
    private String role;
    private String status;

    public LoginRequest() {
    }

    public LoginRequest(String identifier, String password, String role, String status) {
        this.identifier = identifier;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
