package com.Nihatabuzerov3.repository;

import com.Nihatabuzerov3.entites.Course;
import com.Nihatabuzerov3.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    @Query(value = "from Course" )
    List<Course> findAllCourse();
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Integer studentId);

}


