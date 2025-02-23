package com.example.homework_platform.submissions.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubmissionDTO {
    private int id;

    @NotNull(message = "Assignment ID is required")
    private int assignmentId;

    @NotNull(message = "Student ID is required")
    private int studentId;

    private String submissionText;

    @NotEmpty(message = "Submission file cannot be empty")
    @Size(max = 255, message = "File path must be at most 255 characters")
    private String submissionFile;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime submittedAt = LocalDateTime.now();
}
