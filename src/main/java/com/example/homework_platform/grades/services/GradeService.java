package com.example.homework_platform.grades.services;

import com.example.homework_platform.grades.models.Grade;

public interface GradeService {
    Grade getGradeById(int id);
    Grade getGradeByStudentAndAssignment(int studentId, int assignmentId);
    void updateGrade(Grade grade);
}