package com.Nihatabuzerov3.services;

import com.Nihatabuzerov3.dto.DtoCourse;
import com.Nihatabuzerov3.entites.Course;
import com.Nihatabuzerov3.entites.Student;

import java.util.List;

public interface ICourseService {
    public List<String> getCourseByStudentId(Integer StudentId);
    public void addStudentToCourse(Integer studentId, Integer courseId);
    public DtoCourse saveCourse(DtoCourse course);
    public List<DtoCourse> getAllCourse();
    public void deleteCourse(Integer id);
}
