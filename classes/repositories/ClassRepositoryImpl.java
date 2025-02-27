package com.example.homeworkplatform.classes.repositories;

import com.example.homeworkplatform.classes.models.Class;
import com.example.homeworkplatform.students.models.Student;
import com.example.homeworkplatform.teachers.models.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClassRepositoryImpl implements ClassRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClassRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Class> findAll() {
        return jdbcTemplate.query("SELECT * FROM classes", (rs, rowNum) ->
                new Class(rs.getString("id"), rs.getString("name"))
        );
    }

    @Override
    public Optional<Class> findById(String id) {  // Променете типа на id от Long на String
        return jdbcTemplate.queryForObject(
                "SELECT * FROM classes WHERE id = ?",
                new Object[]{id},
                (rs, rowNum) -> Optional.of(new Class(rs.getString("id"), rs.getString("name")))
        );
    }

    @Override
    public List<Student> findStudentsByClassId(String classId) {
        return jdbcTemplate.query(
                "SELECT u.id, u.first_name, u.last_name FROM users u " +
                        "JOIN enrollments e ON u.id = e.student_id " +
                        "WHERE e.class_id = ? AND u.role = 'student'",
                new Object[]{classId},
                (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"))
        );
    }

    @Override
    public List<Teacher> findTeachersByClassId(String classId) {
        return jdbcTemplate.query(
                "SELECT u.id, u.first_name, u.last_name FROM users u " +
                        "JOIN class_teacher ct ON u.id = ct.teacher_id " +
                        "WHERE ct.class_id = ? AND u.role = 'teacher'",
                new Object[]{classId},
                (rs, rowNum) -> new Teacher(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"))
        );
    }

    @Override
    public void assignStudentToClass(String classId, int studentId) {
        jdbcTemplate.update(
                "INSERT INTO enrollments (class_id, student_id) VALUES (?, ?)",
                classId, studentId
        );
    }

    @Override
    public void assignTeacherToClass(String classId, int teacherId) {
        jdbcTemplate.update(
                "INSERT INTO class_teacher (class_id, teacher_id) VALUES (?, ?)",
                classId, teacherId
        );
    }

    @Override
    public void removeStudentFromClass(String classId, int studentId) {
        jdbcTemplate.update(
                "DELETE FROM enrollments WHERE class_id = ? AND student_id = ?",
                classId, studentId
        );
    }

    @Override
    public void removeTeacherFromClass(String classId, int teacherId) {
        jdbcTemplate.update(
                "DELETE FROM class_teacher WHERE class_id = ? AND teacher_id = ?",
                classId, teacherId
        );
    }
}