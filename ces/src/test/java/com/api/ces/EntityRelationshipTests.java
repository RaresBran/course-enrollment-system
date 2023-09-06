package com.api.ces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.api.ces.dto.EnrollmentDto;
import com.api.ces.exception.InvalidIdException;
import com.api.ces.model.Course;
import com.api.ces.model.Enrollment;
import com.api.ces.model.Student;
import com.api.ces.repository.CourseRepository;
import com.api.ces.repository.StudentRepository;
import com.api.ces.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityRelationshipTests {
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Test
    public void testCourseEnrollmentRelationship() {
        Course course = new Course();
        course.setTitle("Course Title");
        course.setDescription("Course Description");
        course = courseRepository.save(course);

        Student student = new Student();
        student.setEmail("student@example.com");
        student.setName("Student Name");
        student = studentRepository.save(student);

        EnrollmentDto enrollmentDto = new EnrollmentDto(course.getId(), student.getId());
        try {
            Enrollment enrollment = enrollmentService.enroll(enrollmentDto);
            assertNotNull(enrollment);
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }

        // Fetch the course from the database and check if it has the enrollment
        Course savedCourse = courseRepository.findById(course.getId()).orElse(null);
        assertNotNull(savedCourse);
        assertEquals(1, savedCourse.getEnrollments().size());

        // Fetch the student from the database and check if it has the enrollment
        Student savedStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(savedStudent);
        assertEquals(1, savedStudent.getEnrollments().size());
    }

}
