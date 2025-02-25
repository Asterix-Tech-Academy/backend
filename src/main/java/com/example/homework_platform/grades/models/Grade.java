package com.example.homework_platform.grades.models;

import java.sql.Timestamp;

public class Grade {
    private int id;
    private int studentId;
    private int assignmentId;
    private double grade;
    private String feedback;
    private Timestamp gradedAt;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public int getAssignmentId() { return assignmentId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    public Timestamp getGradedAt() { return gradedAt; }
    public void setGradedAt(Timestamp gradedAt) { this.gradedAt = gradedAt; }
}