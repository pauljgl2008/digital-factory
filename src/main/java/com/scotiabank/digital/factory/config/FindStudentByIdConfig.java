package com.scotiabank.digital.factory.config;

import com.scotiabank.digital.factory.adapters.out.repository.student.adapter.FindStudentByIdAdapter;
import com.scotiabank.digital.factory.application.usecase.FindStudentByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindStudentByIdConfig {

    @Bean
    public FindStudentByIdUseCase findStudentByIdUseCase(FindStudentByIdAdapter findStudentByIdAdapter) {
        return new FindStudentByIdUseCase(findStudentByIdAdapter);
    }

}
