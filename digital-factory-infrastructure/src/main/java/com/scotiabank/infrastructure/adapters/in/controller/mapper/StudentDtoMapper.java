package com.scotiabank.infrastructure.adapters.in.controller.mapper;

import com.scotiabank.domain.aggregates.Status;
import com.scotiabank.domain.aggregates.StatusMapper;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentRequestDto;
import com.scotiabank.infrastructure.adapters.in.controller.dto.StudentResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper extends StatusMapper {

    Student toStudent(StudentRequestDto studentRequestDto);

    default String fromStatusEnum(Status status) {
        return status.getValue();
    }

    StudentResponseDto toStudentResponse(Student student);

}
