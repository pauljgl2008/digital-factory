package com.scotiabank.digital.factory.config;

import com.scotiabank.digital.factory.adapters.out.repository.student.adapter.FindStudentByIdAdapter;
import com.scotiabank.digital.factory.adapters.out.repository.student.adapter.InsertStudentAdapter;
import com.scotiabank.digital.factory.application.usecase.InsertStudentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertStudentConfig {

    @Bean
    public InsertStudentUseCase insertStudentUseCase(FindStudentByIdAdapter findStudentByIdAdapter,
                                                     InsertStudentAdapter insertStudentAdapter) {
        return new InsertStudentUseCase(findStudentByIdAdapter, insertStudentAdapter);
    }

}
