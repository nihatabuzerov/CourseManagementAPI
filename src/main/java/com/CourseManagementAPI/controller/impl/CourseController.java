package com.CourseManagementAPI.controller.impl;
import com.CourseManagementAPI.dto.AddStudentToCourseRequest;
import com.CourseManagementAPI.dto.DtoCourse;
import com.CourseManagementAPI.services.ICourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final ICourseService courseService;
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping(path = "/studentid/{studentId}")
    public List<String> getCoursesByStudentId(@PathVariable Integer studentId) {
        return courseService.getCourseByStudentId(studentId);
    }

    @PostMapping(path = "/add-student")
    public void addStudentToCourse(@RequestBody AddStudentToCourseRequest request) {
        courseService.addStudentToCourse(request);
    }

    @PostMapping(path = "/courses")
    public ResponseEntity<DtoCourse> saveCourse(@RequestBody @Valid DtoCourse course) {
        DtoCourse savedCourse = courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }


    @GetMapping(path ="/courses" )
    public List<DtoCourse> getAllCourse() {
        return courseService.getAllCourse();
    }

    @DeleteMapping(path = "/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}

