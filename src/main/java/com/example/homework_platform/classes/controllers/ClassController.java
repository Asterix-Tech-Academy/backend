package com.example.homeworkplatform.classes.controllers;

import com.example.homeworkplatform.classes.models.Class;
import com.example.homeworkplatform.classes.services.ClassService;
import com.example.homeworkplatform.students.models.Student;
import com.example.homeworkplatform.teachers.models.Teacher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public Optional<Class> getClassById(@PathVariable String id) {  // Променете типа на id от Long на String
        return classService.getClassById(id);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentsByClassId(@PathVariable String id) {
        return classService.getStudentsByClassId(id);
    }

    @GetMapping("/{id}/teachers")
    public List<Teacher> getTeachersByClassId(@PathVariable String id) {
        return classService.getTeachersByClassId(id);
    }

    @PostMapping("/{id}/assign-student")
    public void assignStudentToClass(@PathVariable String id, @RequestParam int studentId) {
        classService.assignStudentToClass(id, studentId);
    }

    @PostMapping("/{id}/assign-teacher")
    public void assignTeacherToClass(@PathVariable String id, @RequestParam int teacherId) {
        classService.assignTeacherToClass(id, teacherId);
    }

    @PostMapping("/{id}/remove-student")
    public void removeStudentFromClass(@PathVariable String id, @RequestParam int studentId) {
        classService.removeStudentFromClass(id, studentId);
    }

    @PostMapping("/{id}/remove-teacher")
    public void removeTeacherFromClass(@PathVariable String id, @RequestParam int teacherId) {
        classService.removeTeacherFromClass(id, teacherId);
    }
}