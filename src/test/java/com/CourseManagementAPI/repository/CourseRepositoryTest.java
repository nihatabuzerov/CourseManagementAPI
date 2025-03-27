package com.CourseManagementAPI.repository;

import com.CourseManagementAPI.entites.Course;
import com.CourseManagementAPI.entites.Student;
import com.CourseManagementAPI.starter.CourseManagementAPI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = CourseManagementAPI.class)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        courseRepository.deleteAll();
    }

    @Test
    void testFindAll() {
        Course course1 = new Course(null, "Java 101");
        Course course2 = new Course(null, "Spring Boot");
        courseRepository.save(course1);
        courseRepository.save(course2);

        List<Course> courses = courseRepository.findAll();

        assertEquals(2, courses.size());
    }

    @Test
    void testFindByStudents_Id() {
        Student student = new Student(null, "Nihat", "Abuzerov");
        studentRepository.save(student);

        Course course = new Course(null, "Math");
        course.getStudents().add(student);
        student.getCourses().add(course);
        courseRepository.save(course);

        List<Course> courses = courseRepository.findByStudents_Id(student.getId());

        assertFalse(courses.isEmpty());
        assertEquals(1, courses.size());
        assertEquals("Math", courses.get(0).getName());
    }
}