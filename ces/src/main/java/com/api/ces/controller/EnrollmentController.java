package com.api.ces.controller;

import com.api.ces.dto.EnrollmentDto;
import com.api.ces.exception.InvalidIdException;
import com.api.ces.model.Enrollment;
import com.api.ces.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudentToCourse(@RequestBody EnrollmentDto enrollmentDto){
        try {
            return ResponseEntity.ok(enrollmentService.enroll(enrollmentDto));
        } catch (InvalidIdException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
