package com.CourseManagementAPI.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.CourseManagementAPI.Exceptions.StudentNotFoundException;
import com.CourseManagementAPI.dto.DtoCourse;
import com.CourseManagementAPI.mapper.CourseMapper;
import com.CourseManagementAPI.mapper.StudentMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.CourseManagementAPI.dto.DtoStudent;
import com.CourseManagementAPI.dto.DtoStudentIU;
import com.CourseManagementAPI.entites.Course;
import com.CourseManagementAPI.entites.Student;
import com.CourseManagementAPI.repository.StudentRepository;
import com.CourseManagementAPI.services.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {


    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }
    @Transactional
    @Override
    public List<String> getStudentByCourseId(Integer courseId) {

        List<Student> students = studentRepository.findByCourses_Id(courseId);


        List<String> studentNames = new ArrayList<>();
        for (Student student : students) {
            studentNames.add(student.getFirstName());
        }

        return studentNames;
    }

    @Override
    @Transactional
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU) {
        Student student = StudentMapper.INSTANCE.toEntity(dtoStudentIU);
        Student dbStudent = studentRepository.save(student);
        return StudentMapper.INSTANCE.toDto(dbStudent);
    }


    @Override
    @Transactional
    public List<DtoStudent> getAllStudents() {

        List<Student> studentList = studentRepository.findAll();


        List<DtoStudent> dtoList = studentList.stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());

        return dtoList;
    }

    @Transactional
    @Override
    public DtoStudent getStudentById(Integer id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

        DtoStudent dtoStudent = StudentMapper.INSTANCE.toDto(dbStudent);

        if (dbStudent.getCourses() != null && !dbStudent.getCourses().isEmpty()) {
            List<DtoCourse> dtoCourses = dbStudent.getCourses().stream()
                    .map(CourseMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
            dtoStudent.setCourses(dtoCourses);
        }

        return dtoStudent;
    }

    @Transactional
    @Override
    public void deleteStudent(Integer id) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));

        studentRepository.delete(dbStudent);
    }

    @Override
    @Transactional
    public DtoStudent updateStudent(Integer id, DtoStudentIU dtoStudentIU) {
        Student dbStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
        Student updatedStudent = studentMapper.updateEntity(dtoStudentIU, dbStudent);
        Student savedStudent = studentRepository.save(updatedStudent);

        return studentMapper.toDto(savedStudent);
    }
}

