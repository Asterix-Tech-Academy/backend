package com.example.homeworkplatform.classes.repositories;
import com.example.homeworkplatform.classes.models.Class;
import com.example.homeworkplatform.students.models.Student;
import com.example.homeworkplatform.teachers.models.Teacher;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ClassRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public ClassRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Class> findAll() {
//        return jdbcTemplate.query("SELECT * FROM classes", (rs, rowNum) ->
//                new Class(rs.getString("id"), rs.getString("name"), null, null)
//        );
//    }
//
//    public Class findById(String id) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM classes WHERE id = ?",
//                new Object[]{id},
//                (rs, rowNum) -> new Class(rs.getString("id"), rs.getString("name"), null, null)
//        );
//    }
//
//    public List<Student> findStudentsByClassId(String classId) {
//        return jdbcTemplate.query(
//                "SELECT s.* FROM students s JOIN enrollments e ON s.id = e.student_id WHERE e.class_id = ?",
//                new Object[]{classId},
//                (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"))
//        );
//    }
//
//    public List<Teacher> findTeachersByClassId(String classId) {
//        return jdbcTemplate.query(
//                "SELECT t.* FROM teachers t JOIN class_teacher ct ON t.id = ct.teacher_id WHERE ct.class_id = ?",
//                new Object[]{classId},
//                (rs, rowNum) -> new Teacher(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"))
//        );
//    }
//
//    public void assignStudentToClass(String classId, int studentId) {
//        jdbcTemplate.update(
//                "INSERT INTO enrollments (class_id, student_id) VALUES (?, ?)",
//                classId, studentId
//        );
//    }
//
//    public void assignTeacherToClass(String classId, int teacherId) {
//        jdbcTemplate.update(
//                "INSERT INTO class_teacher (class_id, teacher_id) VALUES (?, ?)",
//                classId, teacherId
//        );
//    }
//
//    public void removeStudentFromClass(String classId, int studentId) {
//        jdbcTemplate.update(
//                "DELETE FROM enrollments WHERE class_id = ? AND student_id = ?",
//                classId, studentId
//        );
//    }
//
//    public void removeTeacherFromClass(String classId, int teacherId) {
//        jdbcTemplate.update(
//                "DELETE FROM class_teacher WHERE class_id = ? AND teacher_id = ?",
//                classId, teacherId
//        );
//    }
//}

import java.util.List;
import java.util.Optional;

public interface ClassRepository {
    List<Class> findAll();
    Optional<Class> findById(String id);  // Променете типа на id от Long на String
    List<Student> findStudentsByClassId(String classId);
    List<Teacher> findTeachersByClassId(String classId);
    void assignStudentToClass(String classId, int studentId);
    void assignTeacherToClass(String classId, int teacherId);
    void removeStudentFromClass(String classId, int studentId);
    void removeTeacherFromClass(String classId, int teacherId);
}