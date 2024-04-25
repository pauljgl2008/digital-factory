package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.InsertStudentUseCase;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.InsertStudentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.FindStudentByIdAdapter;
@Configuration
public class InsertStudentConfig {

    @Bean
    public InsertStudentUseCase insertStudentUseCase(FindStudentByIdAdapter findStudentByIdAdapter,
                                                     InsertStudentAdapter insertStudentAdapter) {
        return new InsertStudentUseCase(findStudentByIdAdapter, insertStudentAdapter);
    }

}
