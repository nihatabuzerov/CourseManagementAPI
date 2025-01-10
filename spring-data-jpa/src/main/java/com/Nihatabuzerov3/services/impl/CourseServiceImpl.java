package com.Nihatabuzerov3.services.impl;

import com.Nihatabuzerov3.dto.DtoCourse;
import com.Nihatabuzerov3.entites.Course;
import com.Nihatabuzerov3.entites.Student;
import com.Nihatabuzerov3.repository.CourseRepository;
import com.Nihatabuzerov3.repository.StudentRepository;
import com.Nihatabuzerov3.services.ICourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<String> getCourseByStudentId(Integer studentId) {

            List<Course> courses = courseRepository.findCoursesByStudentId(studentId);
            List<String> courseName = new ArrayList<>();
            for(Course course :courses){
                courseName.add(course.getName());

            }
            return courseName;
    }

    @Override
    public void addStudentToCourse(Integer studentId, Integer courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent() && courseOptional.isPresent()){
            Course c =courseOptional.get();
            Student s = studentOptional.get();

            c.getStudents().add(s);
            courseRepository.save(c);

            s.getCourses().add(c);
            studentRepository.save(s);
        }
    }

    @Override
    public DtoCourse saveCourse(DtoCourse dtoCourse) {

        DtoCourse response = new DtoCourse();
        Course course = new Course();
        BeanUtils.copyProperties(dtoCourse,course);

        Course dbCourse = courseRepository.save(course);
        BeanUtils.copyProperties(dbCourse,response);
        return response;
    }

    @Override
    public List<DtoCourse> getAllCourse() {
     List<DtoCourse> dtoCourse = new ArrayList<>();
     List<Course> courseList = courseRepository.findAll();
     for(Course course : courseList){
         DtoCourse dto = new DtoCourse();
         BeanUtils.copyProperties(course,dto);

         dtoCourse.add(dto);

     } return dtoCourse;
    }

    @Override
    public void deleteCourse(Integer id) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isPresent()){
            courseRepository.delete(optional.get());
        }

    }
}
