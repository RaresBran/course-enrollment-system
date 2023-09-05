package com.api.ces.service;

import com.api.ces.dto.EnrollmentDto;
import com.api.ces.exception.InvalidIdException;
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
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public Enrollment enroll(EnrollmentDto enrollmentDto) throws InvalidIdException {
        Student student = studentRepository.findById(enrollmentDto.getStudentId())
                .orElseThrow(InvalidIdException::new);
        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(InvalidIdException::new);

        Enrollment enrollment = Enrollment.builder()
                .enrollmentDate(LocalDate.now())
                .course(course)
                .student(student)
                .build();

        enrollmentRepository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }
}
