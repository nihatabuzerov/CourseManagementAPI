package com.Nihatabuzerov3.controller;

import java.util.List;

import com.Nihatabuzerov3.dto.DtoStudent;
import com.Nihatabuzerov3.dto.DtoStudentIU;

public interface IStudentController {

	public List<String> getStudentsByCourseId (Integer courseId);

	public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);
	
	public List<DtoStudent> getAllStudents();
	
	public DtoStudent getStudentById(Integer id);
	
	public void deleteStudent(Integer id);
	
	public DtoStudent updateStudent(Integer id , DtoStudentIU dtoStudentIU);
	
}
