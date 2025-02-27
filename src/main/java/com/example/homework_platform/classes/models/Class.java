package com.example.homeworkplatform.classes.models;

import java.util.List;

import com.example.homeworkplatform.students.models.Student;
import com.example.homeworkplatform.teachers.models.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Class {
    private String id;  // Променете типа на id от Long на String
    private String name;

    // Конструктори, гетери и сетери
    public Class() {}

    public Class(String id, String name) {
        this.id = id;
        this.name = name;
    }
// Getters and Setters
}