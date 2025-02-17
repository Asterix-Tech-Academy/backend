package com.example.homework_platform.registration.model;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private String role;
    private LocalDateTime createdAt;
    private String phoneNumber;
    private String address;
    private String subject;
    private Boolean isClassTeacher;
    private String qualification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getIsClassTeacher() {

        return isClassTeacher;
    }

    public void setIsClassTeacher(Boolean classTeacher) {

        isClassTeacher = classTeacher;
    }

    public String getQualification() {

        return qualification;
    }

    public void setQualification(String qualification) {

        this.qualification = qualification;
    }

}

