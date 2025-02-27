package com.example.homeworkplatform.classes.services;

import com.example.homeworkplatform.classes.models.Class;
import com.example.homeworkplatform.classes.repositories.ClassRepository;
import com.example.homeworkplatform.students.models.Student;
import com.example.homeworkplatform.teachers.models.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<Class> getClassById(String id) {  // Променете типа на id от Long на String
        return classRepository.findById(id);
    }

    public List<Student> getStudentsByClassId(String classId) {
        return classRepository.findStudentsByClassId(classId);
    }

    public List<Teacher> getTeachersByClassId(String classId) {
        return classRepository.findTeachersByClassId(classId);
    }

    public void assignStudentToClass(String classId, int studentId) {
        classRepository.assignStudentToClass(classId, studentId);
    }

    public void assignTeacherToClass(String classId, int teacherId) {
        classRepository.assignTeacherToClass(classId, teacherId);
    }

    public void removeStudentFromClass(String classId, int studentId) {
        classRepository.removeStudentFromClass(classId, studentId);
    }

    public void removeTeacherFromClass(String classId, int teacherId) {
        classRepository.removeTeacherFromClass(classId, teacherId);
    }
}