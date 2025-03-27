package com.CourseManagementAPI.controller;
import com.CourseManagementAPI.dto.DtoStudent;
import com.CourseManagementAPI.dto.DtoStudentIU;
import com.CourseManagementAPI.services.IStudentService;
import com.CourseManagementAPI.starter.CourseManagementAPI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CourseManagementAPI.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStudentService studentService;

    @Test
    void getStudentsByCourseId() throws Exception {
        Integer courseId = 1;
        List<String> students = Arrays.asList("Nihat", "Ferid");

        Mockito.when(studentService.getStudentByCourseId(courseId)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/courseid/{courseId}", courseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(students.size()))
                .andExpect(jsonPath("$[0]").value("Nihat"))
                .andExpect(jsonPath("$[1]").value("Ferid"));
    }


    @Test
    void getStudentByCourseId_WhenNoStudent() throws Exception {
        Integer courseId = 2;
        Mockito.when(studentService.getStudentByCourseId(courseId)).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/courseid/{courseId}", courseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0));

    }

    @Test
    void getAllStudents() throws Exception {
        DtoStudent student1 = new DtoStudent(1, "Nihat", "Abuzerov");
        DtoStudent student2 = new DtoStudent(2, "Ferid", "Memmedov");
        List<DtoStudent> students = Arrays.asList(student1, student2);

        Mockito.when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Nihat"))
                .andExpect(jsonPath("$[0].lastName").value("Abuzerov"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Ferid"))
                .andExpect(jsonPath("$[1].lastName").value("Memmedov"));
    }
    @Test
    void getStudentById() throws Exception {
        Integer studentId = 1;
        DtoStudent student = new DtoStudent(studentId, "Nihat", "Abuzerov");

        Mockito.when(studentService.getStudentById(studentId)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/students/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.firstName").value("Nihat"))
                .andExpect(jsonPath("$.lastName").value("Abuzerov"));
    }
    @Test
    void deleteStudent() throws Exception {
        Integer studentId = 1;
        Mockito.doNothing().when(studentService).deleteStudent(studentId);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/students/{id}", studentId))
                .andExpect(status().isNoContent());
    }
        @Test
    void testSaveStudent() throws Exception {
        DtoStudentIU dtoStudentIU = new DtoStudentIU("Nihat", "Abuzerov", "abuzerovniko@gmail.com");
        DtoStudent savedStudent = new DtoStudent(1, "Nihat", "Abuzerov");

        Mockito.when(studentService.saveStudent(dtoStudentIU)).thenReturn(savedStudent);

            mockMvc.perform(MockMvcRequestBuilders.post("/api/student/students")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{ \"firstName\": \"Nihat\", \"lastName\": \"Abuzerov\", \"email\": \"abuzerovniko@gmail.com\" }"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Nihat"))
                    .andExpect(jsonPath("$.lastName").value("Abuzerov"));
    }
    @Test
    void updateStudent() throws Exception {
        Integer studentId = 1;
        DtoStudentIU dtoStudentIU = new DtoStudentIU("Nihat", "Abuzerov", "abuzerovniko@gmail.com");
        DtoStudent updatedStudent = new DtoStudent(1, "Nihat", "Abuzerov");

        Mockito.when(studentService.updateStudent(studentId, dtoStudentIU)).thenReturn(updatedStudent);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/student/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Nihat\", \"lastName\": \"Abuzerov\", \"email\": \"abuzerovniko@gmail.com\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Nihat"))
                .andExpect(jsonPath("$.lastName").value("Abuzerov"));
    }
}
