package com.Nihatabuzerov3.services;

import java.util.List;

import com.Nihatabuzerov3.dto.DtoStudent;
import com.Nihatabuzerov3.dto.DtoStudentIU;
import com.Nihatabuzerov3.entites.Student;

public interface IStudentService {
	public List<String> getStudentByCourseId(Integer courseId);

	public DtoStudent saveStudent(DtoStudentIU student);
	
	public List<DtoStudent> getAllStudents();
	
	public DtoStudent getStudentById(Integer id);
	
	public void deleteStudent(Integer id);
	
	public DtoStudent updateStudent(Integer id , DtoStudentIU dtoStudentIU);
	
}
