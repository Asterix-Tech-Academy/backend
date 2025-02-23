package com.example.homework_platform.submissions.service;

import com.example.homework_platform.submissions.dto.SubmissionDTO;
import com.example.homework_platform.submissions.entity.Submission;
import com.example.homework_platform.submissions.exception.SubmissionNotFoundException;
import com.example.homework_platform.submissions.repository.SubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public SubmissionDTO createSubmission(SubmissionDTO submissionDTO) {
        String uniqueFileName = UUID.randomUUID().toString() + getFileExtension(submissionDTO.getSubmissionFile());

        Submission submission = new Submission();
        submission.setAssignmentId(submissionDTO.getAssignmentId());
        submission.setStudentId(submissionDTO.getStudentId());
        submission.setSubmissionText(submissionDTO.getSubmissionText());
        submission.setSubmissionFile(uniqueFileName);
        submission.setSubmittedAt(submissionDTO.getSubmittedAt());

        submissionRepository.save(submission);

        return toDTO(submission);
    }

    public SubmissionDTO getSubmissionById(int id) {
        return submissionRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new SubmissionNotFoundException("Submission not found"));
    }
    public SubmissionDTO updateSubmission(int id, SubmissionDTO submissionDTO) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new SubmissionNotFoundException("Submission not found"));

        if (!submissionDTO.getSubmissionFile().equals(submission.getSubmissionFile())) {
            submission.setSubmissionFile(UUID.randomUUID().toString() + getFileExtension(submissionDTO.getSubmissionFile()));
        }

        submission.setAssignmentId(submissionDTO.getAssignmentId());
        submission.setStudentId(submissionDTO.getStudentId());
        submission.setSubmissionText(submissionDTO.getSubmissionText());
        submission.setSubmittedAt(submissionDTO.getSubmittedAt());

        submissionRepository.update(submission);

        return toDTO(submission);
    }

    public List<SubmissionDTO> getAllSubmissions() {
        List<Submission> submissions = submissionRepository.findAll();
        List<SubmissionDTO> submissionDTOList = new ArrayList<>();

        for (Submission submission : submissions) {
            SubmissionDTO dto = new SubmissionDTO();
            dto.setId(submission.getId());
            dto.setAssignmentId(submission.getAssignmentId());
            dto.setStudentId(submission.getStudentId());
            dto.setSubmissionText(submission.getSubmissionText());
            dto.setSubmissionFile(submission.getSubmissionFile());
            dto.setSubmittedAt(submission.getSubmittedAt());

            submissionDTOList.add(dto);
        }

        return submissionDTOList;
    }

    private SubmissionDTO toDTO(Submission submission) {
        SubmissionDTO dto = new SubmissionDTO();
        dto.setId(submission.getId());
        dto.setAssignmentId(submission.getAssignmentId());
        dto.setStudentId(submission.getStudentId());
        dto.setSubmissionText(submission.getSubmissionText());
        dto.setSubmissionFile(submission.getSubmissionFile());
        dto.setSubmittedAt(submission.getSubmittedAt());
        return dto;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    public void deleteSubmission(int id) {
        submissionRepository.deleteById(id);
    }
}
