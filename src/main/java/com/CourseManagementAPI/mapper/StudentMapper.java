package com.CourseManagementAPI.mapper;

import com.CourseManagementAPI.dto.DtoStudent;
import com.CourseManagementAPI.dto.DtoStudentIU;
import com.CourseManagementAPI.entites.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(DtoStudentIU dtoStudentIU);

    DtoStudent toDto(Student student);

    @Mapping(target = "id", ignore = true)
    Student updateEntity(DtoStudentIU dtoStudentIU, @MappingTarget Student student);
}
