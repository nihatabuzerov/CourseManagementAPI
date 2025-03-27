package com.CourseManagementAPI.services;
import com.CourseManagementAPI.Exceptions.StudentNotFoundException;
import com.CourseManagementAPI.dto.DtoStudent;
import com.CourseManagementAPI.dto.DtoStudentIU;
import com.CourseManagementAPI.entites.Student;
import com.CourseManagementAPI.mapper.StudentMapper;
import com.CourseManagementAPI.repository.StudentRepository;
import com.CourseManagementAPI.services.impl.StudentServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {com.CourseManagementAPI.starter.CourseManagementAPI.class})
class StudentServiceTest {

	@InjectMocks
	private StudentServiceImpl studentService;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private StudentMapper studentMapper;

	@BeforeEach
	void BeforeTest(){System.out.println("Start test");}

	@AfterEach
	void AfterTest(){System.out.println("Finish test");}

    @Test
	void testGetStudentById() {
		Student mockStudent = new Student();
		mockStudent.setId(1);
		mockStudent.setFirstName("Nihat");
		mockStudent.setLastName("Abuzerov");

		Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(mockStudent));

		DtoStudent dtoStudent = studentService.getStudentById(1);

		assertNotNull(dtoStudent, "Student should not be null");
		assertEquals("wrong Student name","Nihat", dtoStudent.getFirstName());
	}


	@Test
	void testSaveStudent() {

		DtoStudentIU dtoStudent = new DtoStudentIU();
		dtoStudent.setFirstName("nihat");
		dtoStudent.setLastName("abuzerov");

		Student studentEntity = new Student();
		studentEntity.setFirstName("nihat");
		studentEntity.setLastName("abuzerov");


		Mockito.when(studentRepository.save(any(Student.class))).thenReturn(studentEntity);


		DtoStudent savedStudent = studentService.saveStudent(dtoStudent);


		assertNotNull(savedStudent, "Saved student should not be null");
		assertEquals("First name should be nihat", "nihat", savedStudent.getFirstName());
		assertEquals("Last name should be abuzerov", "abuzerov", savedStudent.getLastName());

		verify(studentRepository, times(1)).save(any(Student.class));
	}
	@Test
	void testGetAllStudent(){
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student(1,"nihat","abuzerov"));
		studentList.add(new Student(2,"ferid","memmedov"));

		Mockito.when(studentRepository.findAll()).thenReturn(studentList);

		when(studentMapper.toDto(any(Student.class))).thenAnswer(invocation -> {
			Student student = invocation.getArgument(0);
			return new DtoStudent(student.getId(), student.getFirstName(), student.getLastName());
		});

		List<DtoStudent> dtoStudentList = studentService.getAllStudents();


		assertNotNull(dtoStudentList, "Students should not be null");
		assertFalse(dtoStudentList.isEmpty(), "Students should not be empty");

		dtoStudentList.forEach(student -> {
			System.out.println("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName());
		});

	}

	@Test
	void testDeleteStudent(){
		Integer studentId = 1;
		Student student = new Student(studentId,"nihat","abuzerov");

		Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

		studentService.deleteStudent(studentId);

		verify(studentRepository).delete(student);
	}

	@Test
	void testDeleteStudent_StudentNotFound(){
		Integer studentId = 999;

		when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

		assertThrows(StudentNotFoundException.class , () -> studentService.deleteStudent(studentId));
	}

	@Test
	void testUpdateStudent() {
		Integer studentId = 1;
		DtoStudentIU dtoStudentIU = new DtoStudentIU("Updated Name", "Updated LastName");
		Student dbStudent = new Student(studentId, "Old Name", "Old LastName");
		Student updatedStudent = new Student(studentId, "Updated Name", "Updated LastName");
		Student savedStudent = new Student(studentId, "Updated Name", "Updated LastName");

		when(studentRepository.findById(studentId)).thenReturn(Optional.of(dbStudent));
		when(studentMapper.updateEntity(dtoStudentIU, dbStudent)).thenReturn(updatedStudent);
		when(studentRepository.save(updatedStudent)).thenReturn(savedStudent);

		DtoStudent expectedDto = new DtoStudent(studentId, "Updated Name", "Updated LastName");
		when(studentMapper.toDto(savedStudent)).thenReturn(expectedDto);
		DtoStudent result = studentService.updateStudent(studentId, dtoStudentIU);

		assertNotNull(result, "Result should not be null");


		assertEquals("ID should match", expectedDto.getId(), result.getId());
		assertEquals("First name should match", expectedDto.getFirstName(), result.getFirstName());
		assertEquals("Last name should match", expectedDto.getLastName(), result.getLastName());

		verify(studentRepository).save(updatedStudent);
	}
	@Test
	void testUpdateStudent_StudentNotFound(){
		Integer studentId = 999;
		DtoStudentIU dtoStudentIU = new DtoStudentIU("Updated Name", "Updated LastName");

		Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

		assertThrows(StudentNotFoundException.class,() ->studentService.updateStudent(studentId,dtoStudentIU));

	}

	@Test
	void getStudentByCourseID(){
		Integer courseId = 1;
		Student student1 = new Student(1, "Nihat", "Abuzerov");
		Student student2 = new Student(2, "Ferid", "Memmedov");
		List<Student> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);

		Mockito.when(studentRepository.findByCourses_Id(courseId)).thenReturn(students);

		List<String> result = studentService.getStudentByCourseId(courseId);

		assertNotNull(result, "Student names should not be null");
		assertTrue(result.contains("Nihat"), "List should contain 'Nihat'");
		assertTrue(result.contains("Ferid"), "List should contain 'Ferid'");
	}


}



