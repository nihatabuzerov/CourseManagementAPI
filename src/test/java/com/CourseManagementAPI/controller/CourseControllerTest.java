package com.CourseManagementAPI.controller;
import com.CourseManagementAPI.dto.AddStudentToCourseRequest;
import com.CourseManagementAPI.dto.DtoCourse;
import com.CourseManagementAPI.services.ICourseService;
import com.CourseManagementAPI.starter.CourseManagementAPI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CourseManagementAPI.class)
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICourseService courseService;

    @Test
    void getCoursesByStudentId() throws Exception {
        Integer studentId = 1;
        List<String> expectedCourses = Arrays.asList("Math", "Science", "English");

        Mockito.when(courseService.getCourseByStudentId(studentId)).thenReturn(expectedCourses);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/course/studentid/{studentId}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // HTTP 200: OK
                .andExpect(jsonPath("$[0]").value("Math"))
                .andExpect(jsonPath("$[1]").value("Science"))
                .andExpect(jsonPath("$[2]").value("English"));
    }
    @Test
    void addStudentToCourse() throws Exception {
        AddStudentToCourseRequest request = new AddStudentToCourseRequest();
        request.setStudentId(1);
        request.setCourseId(101);

        doNothing().when(courseService).addStudentToCourse(request);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course/add-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"studentId\": 1, \"courseId\": 101 }"))
                .andExpect(status().isOk());
    }
    @Test
    void saveCourse() throws Exception {
        DtoCourse course = new DtoCourse();
        course.setId(1);
        course.setName("Mathematics");

        Mockito.when(courseService.saveCourse(Mockito.any(DtoCourse.class))).thenReturn(course);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/course/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Mathematics\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mathematics"));
    }
    @Test
    void getAllCourses() throws Exception {
        List<DtoCourse> courses = Arrays.asList(
                new DtoCourse(1, "Mathematics"),
                new DtoCourse(2, "Physics")
        );
        Mockito.when(courseService.getAllCourse()).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/course/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Mathematics"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Physics"));
    }
    @Test
    void deleteCourse() throws Exception {
        Integer courseId = 1;

        doNothing().when(courseService).deleteCourse(courseId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/course/courses/{id}", courseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }



}
