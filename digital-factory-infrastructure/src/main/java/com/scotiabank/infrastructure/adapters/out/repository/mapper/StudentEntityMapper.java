package com.scotiabank.infrastructure.adapters.out.repository.mapper;

import com.scotiabank.domain.aggregates.StatusMapper;
import com.scotiabank.domain.aggregates.Student;
import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentEntityMapper extends StatusMapper {

    Student toStudent(StudentEntity entity);

}
