package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.InsertStudentUseCaseImpl;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.InsertStudentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.FindStudentByIdAdapter;
@Configuration
public class InsertStudentConfig {

    @Bean
    public InsertStudentUseCaseImpl insertStudentUseCase(FindStudentByIdAdapter findStudentByIdAdapter,
                                                         InsertStudentAdapter insertStudentAdapter) {
        return new InsertStudentUseCaseImpl(findStudentByIdAdapter, insertStudentAdapter);
    }

}
