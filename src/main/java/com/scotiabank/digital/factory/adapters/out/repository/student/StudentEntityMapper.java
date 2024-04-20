package com.scotiabank.digital.factory.adapters.out.repository.student;


import com.scotiabank.digital.factory.adapters.out.repository.student.entity.StudentEntity;
import com.scotiabank.digital.factory.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentEntityMapper {

    Student toStudent(StudentEntity myEntity);

}
