package com.CourseManagementAPI.mapper;

import com.CourseManagementAPI.dto.DtoCourse;
import com.CourseManagementAPI.entites.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toEntity(DtoCourse dtoCourse);

    DtoCourse toDto(Course course);
}
