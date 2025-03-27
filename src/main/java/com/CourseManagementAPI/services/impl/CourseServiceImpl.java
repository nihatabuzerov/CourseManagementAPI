package com.CourseManagementAPI.services.impl;

import com.CourseManagementAPI.Exceptions.CourseNotFoundException;
import com.CourseManagementAPI.Exceptions.StudentNotFoundException;
import com.CourseManagementAPI.dto.AddStudentToCourseRequest;
import com.CourseManagementAPI.dto.DtoCourse;
import com.CourseManagementAPI.entites.Course;
import com.CourseManagementAPI.entites.Student;
import com.CourseManagementAPI.mapper.CourseMapper;
import com.CourseManagementAPI.mapper.StudentMapper;
import com.CourseManagementAPI.repository.CourseRepository;
import com.CourseManagementAPI.repository.StudentRepository;
import com.CourseManagementAPI.services.ICourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseMapper = courseMapper;
    }

    @Transactional
    @Override
    public List<String> getCourseByStudentId(Integer studentId) {

        List<Course> courses = courseRepository.findByStudents_Id(studentId);
        List<String> courseName = new ArrayList<>();
        for (Course course : courses) {
            courseName.add(course.getName());

        }
        return courseName;
    }

    @Transactional
    @Override
    public void addStudentToCourse(AddStudentToCourseRequest request) {
        Integer studentId = request.getStudentId();
        Integer courseId = request.getCourseId();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + courseId + " not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));

        course.getStudents().add(student);
        courseRepository.save(course);

        student.getCourses().add(course);
        studentRepository.save(student);
    }


    @Transactional
    @Override
    public DtoCourse saveCourse(DtoCourse dtoCourse) {
        Course course = courseMapper.toEntity(dtoCourse);
        Course dbCourse = courseRepository.save(course);
        DtoCourse response = courseMapper.toDto(dbCourse);

        return response;
    }

    @Transactional
    @Override
    public List<DtoCourse> getAllCourse() {
        List<Course> courseList = courseRepository.findAll();
        List<DtoCourse> dtoCourseList = courseList.stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());

        return dtoCourseList;
    }

    @Transactional
    @Override
    public void deleteCourse(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + id + " not found"));

        courseRepository.delete(course);
    }
}
