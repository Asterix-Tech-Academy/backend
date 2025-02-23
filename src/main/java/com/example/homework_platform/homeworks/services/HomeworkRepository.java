package com.example.homework_platform.homeworks.services;

import com.example.homework_platform.homeworks.models.Homework;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HomeworkRepository {
    private final JdbcTemplate jdbcTemplate;

    public HomeworkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveHomework(Homework homework) {
        return jdbcTemplate.update("INSERT INTO Assignments (title, description, deadline, teacher_id, class_id, group_id, description_file, assigned_at, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                homework.getTitle(), homework.getDescription(), homework.getDeadline(),
                homework.getTeacherId(), homework.getClassId(), homework.getGroupId(),
                homework.getDescriptionFile(), homework.getAssignedAt(), homework.getCreatedAt(), homework.getUpdatedAt());
    }

    public Homework findHomeworkById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Assignments WHERE id = ?",
                    (rs, rowNum) -> new Homework(rs.getLong("id"), rs.getString("title"), rs.getString("description"),
                            rs.getTimestamp("assigned_at"), rs.getDate("deadline"), rs.getLong("teacher_id"),
                            rs.getString("class_id"), rs.getInt("group_id"), rs.getString("description_file"),
                            rs.getTimestamp("created_at"), rs.getTimestamp("updated_at")), id);
        } catch (DataAccessException e) {
            return null; // Return null if homework is not found
        }
    }

    public int updateHomework(Long id, Homework homework) {
        return jdbcTemplate.update("UPDATE Assignments SET title = ?, description = ?, deadline = ?, teacher_id = ?, class_id = ?, group_id = ?, description_file = ? WHERE id = ?",
                homework.getTitle(), homework.getDescription(), homework.getDeadline(),
                homework.getTeacherId(), homework.getClassId(), homework.getGroupId(),
                homework.getDescriptionFile(), id);
    }

    public int deleteHomework(Long id) {
        return jdbcTemplate.update("DELETE FROM Assignments WHERE id = ?", id);
    }
}