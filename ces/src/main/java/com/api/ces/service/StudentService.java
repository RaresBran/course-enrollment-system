package com.api.ces.service;

import com.api.ces.dto.EnrollmentDto;
import com.api.ces.model.Course;
import com.api.ces.model.Enrollment;
import com.api.ces.model.Student;
import com.api.ces.repository.CourseRepository;
import com.api.ces.repository.EnrollmentRepository;
import com.api.ces.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
