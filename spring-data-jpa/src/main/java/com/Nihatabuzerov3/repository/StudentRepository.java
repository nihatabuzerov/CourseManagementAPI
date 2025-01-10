package com.Nihatabuzerov3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Nihatabuzerov3.entites.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	@Query(value = "from Student" , nativeQuery=false)
	List<Student> findAllStudents();
	
	@Query(value = "from Student s WHERE s.id= :studentId")
	Optional<Student> findStudentById(Integer studentId);

	@Query("SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId")
	List<Student> findStudentsByCourseId(@Param("courseId") Integer courseId);
}
