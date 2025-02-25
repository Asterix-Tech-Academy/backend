package com.example.homework_platform.grades.services;

import com.example.homework_platform.grades.models.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public Grade getGradeById(int id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade getGradeByStudentAndAssignment(int studentId, int assignmentId) {
        return gradeRepository.findByStudentIdAndAssignmentId(studentId, assignmentId);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeRepository.update(grade);
    }
}