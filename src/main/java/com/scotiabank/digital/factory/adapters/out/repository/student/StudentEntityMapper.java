package com.scotiabank.digital.factory.adapters.out.repository.student;


import com.scotiabank.digital.factory.adapters.out.repository.student.entity.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentEntityMapper {

    StudentEntity toStudentEntity(com.scotiabank.digital.factory.domain.model.Student student);

    com.scotiabank.digital.factory.domain.model.Student toStudent(StudentEntity myEntity);

}
