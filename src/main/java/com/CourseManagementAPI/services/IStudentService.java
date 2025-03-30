package com.CourseManagementAPI.services;

import java.util.List;

import com.CourseManagementAPI.dto.DtoStudent;
import com.CourseManagementAPI.dto.DtoStudentIU;

public interface IStudentService {
    public List<String> getStudentByCourseId(Integer courseId);

    public DtoStudent saveStudent(DtoStudentIU student);

    public List<DtoStudent> getAllStudents();

    public DtoStudent getStudentById(Integer id);

    public void deleteStudent(Integer id);

    public DtoStudent updateStudent(Integer id , DtoStudentIU dtoStudentIU);

}
