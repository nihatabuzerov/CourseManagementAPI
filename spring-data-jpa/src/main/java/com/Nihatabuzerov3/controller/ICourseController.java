package com.Nihatabuzerov3.controller;

import com.Nihatabuzerov3.dto.DtoCourse;
import com.Nihatabuzerov3.entites.Course;

import java.util.List;

public interface ICourseController {
    public List<String> getCoursesByStudentId (Integer studentId);

    public void addStudentToCourse(Integer studentId,Integer courseId);

    public DtoCourse saveCourse(DtoCourse course);

    public List<DtoCourse> getAllCourse();

    public void deleteCourse(Integer id);
}
