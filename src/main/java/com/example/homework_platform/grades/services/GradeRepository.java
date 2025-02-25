package com.example.homework_platform.grades.services;

import com.example.homework_platform.grades.models.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GradeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper за преобразуване на ResultSet в обект Grade
    private final RowMapper<Grade> gradeRowMapper = (rs, rowNum) -> {
        Grade grade = new Grade();
        grade.setId(rs.getInt("id"));
        grade.setStudentId(rs.getInt("student_id"));
        grade.setAssignmentId(rs.getInt("assignment_id"));
        grade.setGrade(rs.getDouble("grade"));
        grade.setFeedback(rs.getString("feedback"));
        grade.setGradedAt(rs.getTimestamp("graded_at"));
        return grade;
    };

    // Взимане на оценка по ID
    public Grade findById(int id) {
        String sql = "SELECT * FROM Grades WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, gradeRowMapper, id);
    }

    // Взимане на оценка по studentId и assignmentId
    public Grade findByStudentIdAndAssignmentId(int studentId, int assignmentId) {
        String sql = "SELECT * FROM Grades WHERE student_id = ? AND assignment_id = ?";
        return jdbcTemplate.queryForObject(sql, gradeRowMapper, studentId, assignmentId);
    }

    // Актуализиране на оценка
    public void update(Grade grade) {
        String sql = "UPDATE Grades SET grade = ?, feedback = ? WHERE id = ?";
        jdbcTemplate.update(sql, grade.getGrade(), grade.getFeedback(), grade.getId());
    }
}