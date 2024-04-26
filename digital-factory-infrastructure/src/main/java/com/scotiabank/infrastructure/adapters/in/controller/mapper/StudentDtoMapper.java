package com.scotiabank.infrastructure.adapters.in.controller.mapper;


import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentRequestDto;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    Student toStudent(StudentRequestDto studentRequestDto);

    default Status toStatusEnum(String estado) {
        return Status.fromValor(estado);
    }

    default String fromStatusEnum(Status estado) {
        return estado.getValor();
    }

    StudentResponseDto toStudentResponse(Student student);

}
