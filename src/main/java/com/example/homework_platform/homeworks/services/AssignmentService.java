package com.example.homework_platform.homeworks.services;

import com.example.homework_platform.homeworks.models.Assignment;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    private final AssignmentRepository repository;

    public AssignmentService(AssignmentRepository repository) {
        this.repository = repository;
    }

    public void createAssignment(Assignment assignment) {
        if (assignment.getTitle() == null || assignment.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        repository.save(assignment);
    }

    public Assignment getAssignment(Long id) {
        Assignment assignment = repository.findById(id);
        if (assignment == null) {
            throw new RuntimeException("Assignment not found with id: " + id);
        }
        return assignment;
    }

    public void updateAssignment(Long id, Assignment updatedAssignment) {
        if (updatedAssignment.getTitle() == null || updatedAssignment.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        int rowsUpdated = repository.update(id, updatedAssignment);
        if (rowsUpdated == 0) {
            throw new RuntimeException("Assignment not found with id: " + id);
        }
    }

    public void deleteAssignment(Long id) {
        int rowsDeleted = repository.delete(id);
        if (rowsDeleted == 0) {
            throw new RuntimeException("Assignment not found with id: " + id);
        }
    }
}