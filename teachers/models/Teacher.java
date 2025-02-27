package com.example.homeworkplatform.teachers.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher {
    private int id;
    private String firstName;
    private String lastName;

    public Teacher(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}