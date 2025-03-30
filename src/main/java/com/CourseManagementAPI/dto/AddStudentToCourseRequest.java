package com.CourseManagementAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudentToCourseRequest {

    private Integer studentId;
    private Integer courseId;



}