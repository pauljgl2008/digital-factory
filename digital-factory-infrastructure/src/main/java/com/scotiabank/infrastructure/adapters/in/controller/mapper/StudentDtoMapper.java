package com.scotiabank.infrastructure.adapters.in.controller.mapper;


import com.scotiabank.domain.model.Status;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentRequestDto;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentResponseDto;
import com.scotiabank.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    Student toStudent(StudentRequestDto studentRequestDto);

    default Status toStatusEnum(String estado) {
        return Status.fromValor(estado.toLowerCase());
    }

    default String fromStatusEnum(Status estado) {
        return estado.getValor().toLowerCase();
    }

    StudentResponseDto toStudentResponse(Student student);

}
