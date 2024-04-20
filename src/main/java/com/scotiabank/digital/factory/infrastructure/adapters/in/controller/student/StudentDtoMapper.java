package com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student;


import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto.StudentRequestDto;
import com.scotiabank.digital.factory.infrastructure.adapters.in.controller.student.dto.StudentResponseDto;
import com.scotiabank.digital.factory.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    Student toStudent(StudentRequestDto studentRequestDto);

    StudentResponseDto toStudentResponse(Student student);

}
