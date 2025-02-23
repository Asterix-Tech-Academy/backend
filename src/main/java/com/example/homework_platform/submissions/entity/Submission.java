package com.example.homework_platform.submissions.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    private int id;
    private int assignmentId;
    private int studentId;
    private String submissionText;
    private String submissionFile;
    private LocalDateTime submittedAt = LocalDateTime.now();
}
