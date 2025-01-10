package com.Nihatabuzerov3.controller.impl;

import com.Nihatabuzerov3.controller.ICourseController;
import com.Nihatabuzerov3.dto.DtoCourse;
import com.Nihatabuzerov3.entites.Course;
import com.Nihatabuzerov3.services.ICourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/course")
public class CourseController implements ICourseController {

    @Autowired
    private ICourseService courseService;

    @Override
    @GetMapping(path = "/studentid/{studentId}")
    public List<String> getCoursesByStudentId(@PathVariable Integer studentId) {
        return courseService.getCourseByStudentId(studentId);
    }

    @PostMapping(path = "/add/{studentId}/{courseId}")
    @Override
    public void addStudentToCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {

        courseService.addStudentToCourse(studentId, courseId);

    }


    @PostMapping(path = "/save")
    @Override
    public DtoCourse saveCourse(@RequestBody @Valid DtoCourse course) {

        return courseService.saveCourse(course);
    }

    @GetMapping(path ="/list" )
    @Override
    public List<DtoCourse> getAllCourse() {
        return courseService.getAllCourse();
    }
    @DeleteMapping(path = "/delete/{id}")
    @Override
    public void deleteCourse(Integer id) {
        courseService.deleteCourse(id);

    }


}

