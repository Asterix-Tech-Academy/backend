package com.example.homework_platform.submissions.controller;

import com.example.homework_platform.submissions.dto.SubmissionDTO;
import com.example.homework_platform.submissions.exception.SubmissionNotFoundException;
import com.example.homework_platform.submissions.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<SubmissionDTO> createSubmission(@Valid @RequestBody SubmissionDTO submissionDTO) {
        SubmissionDTO createdSubmission = submissionService.createSubmission(submissionDTO);
        return new ResponseEntity<>(createdSubmission, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionDTO> getSubmissionById(@PathVariable int id) {
        SubmissionDTO submission = submissionService.getSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubmissionDTO> updateSubmission(
            @PathVariable int id, @Valid @RequestBody SubmissionDTO submissionDTO) {
        SubmissionDTO updatedSubmission = submissionService.updateSubmission(id, submissionDTO);
        return new ResponseEntity<>(updatedSubmission, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> getAllSubmissions() {
        List<SubmissionDTO> submissions = submissionService.getAllSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable int id) {
        submissionService.deleteSubmission(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(SubmissionNotFoundException.class)
    public ResponseEntity<String> handleSubmissionNotFound(SubmissionNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
