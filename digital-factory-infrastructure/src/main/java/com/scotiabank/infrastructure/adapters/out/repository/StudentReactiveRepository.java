package com.scotiabank.infrastructure.adapters.out.repository;

import com.scotiabank.infrastructure.adapters.out.repository.entity.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentReactiveRepository extends ReactiveCrudRepository<StudentEntity, String> {

}
