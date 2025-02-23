package com.example.homework_platform.submissions.repository;

import com.example.homework_platform.submissions.entity.Submission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class SubmissionRepository {

    private final JdbcTemplate jdbcTemplate;

    public SubmissionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Submission submission) {
        return jdbcTemplate.update(
                "INSERT INTO submissions (assignment_id, student_id, submission_text, submission_file, submitted_at) VALUES (?, ?, ?, ?, ?)",
                submission.getAssignmentId(), submission.getStudentId(), submission.getSubmissionText(),
                submission.getSubmissionFile(), submission.getSubmittedAt()
        );
    }

    public int update(Submission submission) {
        return jdbcTemplate.update(
                "UPDATE submissions SET assignment_id = ?, student_id = ?, submission_text = ?, submission_file = ?, submitted_at = ? WHERE id = ?",
                submission.getAssignmentId(), submission.getStudentId(), submission.getSubmissionText(),
                submission.getSubmissionFile(), submission.getSubmittedAt(), submission.getId()
        );
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM submissions WHERE id = ?", id);
    }

    public List<Submission> findAll() {
        return jdbcTemplate.query("SELECT * FROM submissions", new SubmissionRowMapper());
    }

    public Optional<Submission> findById(int id) {
        List<Submission> submissions = jdbcTemplate.query("SELECT * FROM submissions WHERE id = ?", new SubmissionRowMapper(), id);
        return submissions.stream().findFirst();
    }

    public List<Submission> findByAssignmentId(int assignmentId) {
        return jdbcTemplate.query("SELECT * FROM submissions WHERE assignment_id = ?", new SubmissionRowMapper(), assignmentId);
    }

    public List<Submission> findByStudentId(int studentId) {
        return jdbcTemplate.query("SELECT * FROM submissions WHERE student_id = ?", new SubmissionRowMapper(), studentId);
    }

    private static class SubmissionRowMapper implements RowMapper<Submission> {
        @Override
        public Submission mapRow(ResultSet rs, int rowNum) throws SQLException {
            Submission submission = new Submission();
            submission.setId(rs.getInt("id"));
            submission.setAssignmentId(rs.getInt("assignment_id"));
            submission.setStudentId(rs.getInt("student_id"));
            submission.setSubmissionText(rs.getString("submission_text"));
            submission.setSubmissionFile(rs.getString("submission_file"));
            submission.setSubmittedAt(rs.getTimestamp("submitted_at").toLocalDateTime());
            return submission;
        }
    }
}
