package com.scotiabank.infrastructure.adapters.out.repository;


import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import com.scotiabank.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentEntityMapper {

    Student toStudent(StudentEntity myEntity);

}
