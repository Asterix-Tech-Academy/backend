package com.example.homework_platform.grades.controllers;

import com.example.homework_platform.grades.models.Grade;
import com.example.homework_platform.grades.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/{id}")
    public Grade getGrade(@PathVariable int id) {
        return gradeService.getGradeById(id);
    }

    @GetMapping("/student/{studentId}/assignment/{assignmentId}")
    public Grade getGradeByStudentAndAssignment(@PathVariable int studentId, @PathVariable int assignmentId) {
        return gradeService.getGradeByStudentAndAssignment(studentId, assignmentId);
    }

    @PutMapping("/{id}")
    public void updateGrade(@PathVariable int id, @RequestBody Grade grade) {
        grade.setId(id);
        gradeService.updateGrade(grade);
    }
}