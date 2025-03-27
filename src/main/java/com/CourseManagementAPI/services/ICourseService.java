package com.CourseManagementAPI.services;

import com.CourseManagementAPI.dto.AddStudentToCourseRequest;
import com.CourseManagementAPI.dto.DtoCourse;

import java.util.List;

public interface ICourseService {
    public List<String> getCourseByStudentId(Integer StudentId);
    public void addStudentToCourse(AddStudentToCourseRequest request);
    public DtoCourse saveCourse(DtoCourse course);
    public List<DtoCourse> getAllCourse();
    public void deleteCourse(Integer id);
}
