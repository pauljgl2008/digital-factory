package com.scotiabank.infrastructure.adapters.out.repository.mapper;


import com.scotiabank.domain.model.Status;
import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import com.scotiabank.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentEntityMapper {

    Student toStudent(StudentEntity myEntity);

    default Status toStatusEnum(String estado) {
        return Status.fromValor(estado.toLowerCase());
    }

}
