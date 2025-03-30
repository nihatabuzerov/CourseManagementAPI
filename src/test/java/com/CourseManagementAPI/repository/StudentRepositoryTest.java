package com.CourseManagementAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.CourseManagementAPI.starter.CourseManagementAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.CourseManagementAPI.entites.Student;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = CourseManagementAPI.class)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = new Student();
        student1.setFirstName("nihat");
        student1.setLastName("abuzerov");

        student2 = new Student();
        student2.setFirstName("ferid");
        student2.setLastName("memmedov");

        studentRepository.saveAll(List.of(student1, student2));
    }


    @Test
    void testFindAll() {
        List<Student> students = studentRepository.findAll();
        assertThat(students).hasSize(2);
    }

    @Test
    void testFindById() {
        Optional<Student> foundStudent = studentRepository.findById(student1.getId());
        assertThat(foundStudent).isPresent();
        assertThat(foundStudent.get().getFirstName()).isEqualTo("nihat");
    }

    @Test
    void testFindByCourses_Id() {
        List<Student> students = studentRepository.findByCourses_Id(1);
        assertThat(students).isEmpty();
    }
}
