package com.CourseManagementAPI.repository;

import com.CourseManagementAPI.entites.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAll();

    List<Course> findByStudents_Id(Integer studentId);
}


