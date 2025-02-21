package com.example.homework_platform.homeworks.services;

import com.example.homework_platform.homeworks.models.Assignment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public AssignmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Assignment assignment) {
        return jdbcTemplate.update("INSERT INTO Assignments (title, description, deadline, teacher_id, class_id, group_id, description_file) VALUES (?, ?, ?, ?, ?, ?, ?)",
                assignment.getTitle(), assignment.getDescription(), assignment.getDeadline(),
                assignment.getTeacherId(), assignment.getClassId(), assignment.getGroupId(),
                assignment.getDescriptionFile());
    }

    public Assignment findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Assignments WHERE id = ?",
                    (rs, rowNum) -> new Assignment(rs.getLong("id"), rs.getString("title"), rs.getString("description"),
                            rs.getTimestamp("assigned_at"), rs.getDate("deadline"), rs.getLong("teacher_id"),
                            rs.getString("class_id"), rs.getInt("group_id"), rs.getString("description_file"),
                            rs.getTimestamp("created_at"), rs.getTimestamp("updated_at")), id);
        } catch (DataAccessException e) {
            return null; // Return null if assignment is not found
        }
    }

    public int update(Long id, Assignment assignment) {
        return jdbcTemplate.update("UPDATE Assignments SET title = ?, description = ?, deadline = ?, teacher_id = ?, class_id = ?, group_id = ?, description_file = ? WHERE id = ?",
                assignment.getTitle(), assignment.getDescription(), assignment.getDeadline(),
                assignment.getTeacherId(), assignment.getClassId(), assignment.getGroupId(),
                assignment.getDescriptionFile(), id);
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM Assignments WHERE id = ?", id);
    }
}