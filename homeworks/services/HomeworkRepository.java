//package com.example.homework_platform.homeworks.services;
//
//import com.example.homework_platform.homeworks.models.Homework;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class HomeworkRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    public HomeworkRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public int saveHomework(Homework homework) {
//        return jdbcTemplate.update("INSERT INTO Assignments (title, description, deadline, teacher_id, class_id, group_id, description_file, assigned_at, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
//                homework.getTitle(), homework.getDescription(), homework.getDeadline(),
//                homework.getTeacherId(), homework.getClassId(), homework.getGroupId(),
//                homework.getDescriptionFile(), homework.getAssignedAt(), homework.getCreatedAt(), homework.getUpdatedAt());
//    }
//
//    public Homework findHomeworkById(Long id) {
//        try {
//            return jdbcTemplate.queryForObject("SELECT * FROM Assignments WHERE id = ?",
//                    (rs, rowNum) -> new Homework(rs.getLong("id"), rs.getString("title"), rs.getString("description"),
//                            rs.getTimestamp("assigned_at"), rs.getDate("deadline"), rs.getLong("teacher_id"),
//                            rs.getString("class_id"), rs.getInt("group_id"), rs.getString("description_file"),
//                            rs.getTimestamp("created_at"), rs.getTimestamp("updated_at")), id);
//        } catch (DataAccessException e) {
//            return null; // Return null if homework is not found
//        }
//    }
//
//    public int updateHomework(Long id, Homework homework) {
//        return jdbcTemplate.update("UPDATE Assignments SET title = ?, description = ?, deadline = ?, teacher_id = ?, class_id = ?, group_id = ?, description_file = ? WHERE id = ?",
//                homework.getTitle(), homework.getDescription(), homework.getDeadline(),
//                homework.getTeacherId(), homework.getClassId(), homework.getGroupId(),
//                homework.getDescriptionFile(), id);
//    }
//
//    public int deleteHomework(Long id) {
//        return jdbcTemplate.update("DELETE FROM Assignments WHERE id = ?", id);
//    }
//}

package com.example.homeworkplatform.homeworks.services;

import com.example.homeworkplatform.homeworks.models.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class HomeworkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HomeworkRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveHomework(Homework homework) {
        String sql = "INSERT INTO Assignments (title, description, deadline, teacher_id, class_id, group_id, description_file, assigned_at, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                homework.getTitle(), homework.getDescription(), homework.getDeadline(),
                homework.getTeacherId(), homework.getClassId(), homework.getGroupId(),
                homework.getDescriptionFile(), homework.getAssignedAt(), homework.getCreatedAt(), homework.getUpdatedAt());
    }

    public Homework findHomeworkById(Long id) {
        String sql = "SELECT * FROM Assignments WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, homeworkRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public int updateHomework(Long id, Homework homework) {
        String sql = "UPDATE Assignments SET title = ?, description = ?, deadline = ?, teacher_id = ?, class_id = ?, group_id = ?, description_file = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                homework.getTitle(), homework.getDescription(), homework.getDeadline(),
                homework.getTeacherId(), homework.getClassId(), homework.getGroupId(),
                homework.getDescriptionFile(), id);
    }

    public int deleteHomework(Long id) {
        String sql = "DELETE FROM Assignments WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private RowMapper<Homework> homeworkRowMapper() {
        return (rs, rowNum) -> {
            Homework homework = new Homework();
            homework.setId(rs.getLong("id"));
            homework.setTitle(rs.getString("title"));
            homework.setDescription(rs.getString("description"));
            homework.setAssignedAt(rs.getTimestamp("assigned_at"));
            homework.setDeadline(rs.getDate("deadline"));
            homework.setTeacherId(rs.getLong("teacher_id"));
            homework.setClassId(rs.getString("class_id"));
            homework.setGroupId(rs.getInt("group_id"));
            homework.setDescriptionFile(rs.getString("description_file"));
            homework.setCreatedAt(rs.getTimestamp("created_at"));
            homework.setUpdatedAt(rs.getTimestamp("updated_at"));
            return homework;
        };
    }
}