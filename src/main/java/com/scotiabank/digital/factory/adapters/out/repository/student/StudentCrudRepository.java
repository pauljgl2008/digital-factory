package com.scotiabank.digital.factory.adapters.out.repository.student;

import com.scotiabank.digital.factory.adapters.out.repository.student.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentCrudRepository extends ReactiveCrudRepository<StudentEntity, String> {


}
