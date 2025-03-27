Course Management API

Overview
This is a Spring Boot REST API for managing courses and students.It allows users to create, retrieve, update, and delete courses and students, as well as manage relationships between them.

Technologies Used:
Java
Spring Boot
Spring MVC
Hibernate (JPA)
PostgreSQL
Lombok
Maven

Endpoints

Course Endpoints:
GET	/api/course/courses	Get all courses
POST	/api/course/courses	Create a new course
DELETE	/api/course/courses/{id}	Delete a course by ID
GET	/api/course/studentid/{studentId}	Get courses by student ID

Student Endpoints
GET	/api/course/students	Get all students
GET	/api/course/students/{id}	Get a student by ID
POST	/api/course/students	Create a new student
PUT	/api/course/students/{id}	Update a student by ID
DELETE	/api/course/students/{id}	Delete a student by ID
GET	/api/course/courseid/{courseId}	Get students by course ID
POST	/api/course/add-student	Add a student to a course

Setup Instructions
Clone the repository:
git clone https://github.com/nihatabuzerov/first-project-spring.git
cd first-project-spring 

Configure the database in application.properties:
spring.application.name=CourseManagementAPI  
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres  
spring.datasource.username=postgres  
spring.datasource.password=1
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  
spring.jpa.properties.hibernate.format_sql=true

Build and run the project:
mvn clean install  
mvn spring-boot:run  

Access the API at http://localhost:8080/api/course
