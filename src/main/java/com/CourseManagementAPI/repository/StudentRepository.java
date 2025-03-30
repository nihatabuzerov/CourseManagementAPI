package com.CourseManagementAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.CourseManagementAPI.entites.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAll();

    Optional<Student> findById(Integer studentId);

    List<Student> findByCourses_Id(Integer courseId);
}