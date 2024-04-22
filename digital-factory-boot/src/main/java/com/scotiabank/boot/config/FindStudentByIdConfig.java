package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.FindStudentByIdUseCase;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.FindStudentByIdAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindStudentByIdConfig {

    @Bean
    public FindStudentByIdUseCase findStudentByIdUseCase(FindStudentByIdAdapter findStudentByIdAdapter) {
        return new FindStudentByIdUseCase(findStudentByIdAdapter);
    }

}
