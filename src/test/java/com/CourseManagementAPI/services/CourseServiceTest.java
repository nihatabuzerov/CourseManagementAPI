package com.CourseManagementAPI.services;
import com.CourseManagementAPI.dto.AddStudentToCourseRequest;
import com.CourseManagementAPI.dto.DtoCourse;
import com.CourseManagementAPI.entites.Course;
import com.CourseManagementAPI.entites.Student;
import com.CourseManagementAPI.mapper.CourseMapper;
import com.CourseManagementAPI.repository.CourseRepository;
import com.CourseManagementAPI.repository.StudentRepository;
import com.CourseManagementAPI.services.impl.CourseServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest(classes = {com.CourseManagementAPI.starter.CourseManagementAPI.class})
public class CourseServiceTest {


    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void BeforeTest(){System.out.println("Start test");}

    @AfterEach
    void AfterTest(){System.out.println("Finish test");}


    @Test
    void testSaveCourse() {
        // Given
        DtoCourse dtoCourse = new DtoCourse();
        dtoCourse.setName("Math");

        Course courseEntity = new Course();
        courseEntity.setName("Math");

        Course savedCourseEntity = new Course();
        savedCourseEntity.setId(1);
        savedCourseEntity.setName("Math");

        DtoCourse savedDtoCourse = new DtoCourse();
        savedDtoCourse.setId(1);
        savedDtoCourse.setName("Math");

        when(courseMapper.toEntity(dtoCourse)).thenReturn(courseEntity);
        when(courseRepository.save(courseEntity)).thenReturn(savedCourseEntity);
        when(courseMapper.toDto(savedCourseEntity)).thenReturn(savedDtoCourse);

        DtoCourse result = courseService.saveCourse(dtoCourse);

        assertNotNull("method returned null",result);
        assertEquals("returned DtoCourse ID is not as expected",1, result.getId());
        assertEquals("returned DtoCourse name is not as expected","Math", result.getName());

    }



    @Test
    void testGetAllCourse() {

        Course course1 = new Course();
        course1.setId(1);
        course1.setName("Math");

        Course course2 = new Course();
        course2.setId(2);
        course2.setName("Physic");

        List<Course> courseList = Arrays.asList(course1, course2);

        DtoCourse dtoCourse1 = new DtoCourse();
        dtoCourse1.setId(1);
        dtoCourse1.setName("Math");

        DtoCourse dtoCourse2 = new DtoCourse();
        dtoCourse2.setId(2);
        dtoCourse2.setName("Physic");

        when(courseRepository.findAll()).thenReturn(courseList);
        when(courseMapper.toDto(course1)).thenReturn(dtoCourse1);
        when(courseMapper.toDto(course2)).thenReturn(dtoCourse2);


        List<DtoCourse> result = courseService.getAllCourse();


        assertNotNull("The result should not be null", result);
        assertEquals("The result size is incorrect", 2, result.size());

        DtoCourse firstCourse = result.get(0);
        assertEquals("First course ID is incorrect", 1L, firstCourse.getId().longValue());
        assertEquals("First course name is incorrect", "Math", firstCourse.getName());

        DtoCourse secondCourse = result.get(1);
        assertEquals("Second course ID is incorrect", 2L, secondCourse.getId().longValue());
        assertEquals("Second course name is incorrect", "Physic", secondCourse.getName());

        verify(courseRepository, times(1)).findAll();
        verify(courseMapper, times(1)).toDto(course1);
        verify(courseMapper, times(1)).toDto(course2);
    }

    @Test
    void testDeleteCourse(){
        Course course = new Course(1,"Math");

        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        courseService.deleteCourse(1);

        assertEquals("id is not excepted",1,course.getId());
        assertNotNull("course should be not null",course);

        verify(courseRepository,times(1)).findById(1);
        verify(courseRepository,times(1)).delete(course);

    }
    @Test
    void testGetStudentByCourseID() {

        Integer studentId = 1;

        Course course1 = new Course();
        course1.setId(1);
        course1.setName("Math");

        Course course2 = new Course();
        course2.setId(2);
        course2.setName("Science");

        Mockito.when(courseRepository.findByStudents_Id(studentId)).thenReturn(Arrays.asList(course1, course2));

        List<String> courseNames = courseService.getCourseByStudentId(studentId);

        assertNotNull("Course names list should not be null", courseNames);
        assertEquals("There should be 2 courses for the student", 2, courseNames.size());
        assertTrue("Course names should contain 'Math'", courseNames.contains("Math"));
        assertTrue("Course names should contain 'Science'", courseNames.contains("Science"));

        verify(courseRepository, times(1)).findByStudents_Id(studentId);
    }

    @Test
    void testAddStudentToCourse() {
        Integer studentId = 1;
        Integer courseId = 1;

        Course course = new Course();
        course.setId(courseId);
        course.setName("Math");

        Student student = new Student();
        student.setId(studentId);
        student.setFirstName("Nihat");
        student.setLastName("Abuzerov");

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        AddStudentToCourseRequest request = new AddStudentToCourseRequest();
        request.setStudentId(studentId);
        request.setCourseId(courseId);

        courseService.addStudentToCourse(request);

        assertTrue("Student should be added to the course", course.getStudents().contains(student));
        assertTrue("Course should be added to the student", student.getCourses().contains(course));

        verify(courseRepository, times(1)).save(course);
        verify(studentRepository, times(1)).save(student);
    }
}


