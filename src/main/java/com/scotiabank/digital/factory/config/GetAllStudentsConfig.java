package com.scotiabank.digital.factory.config;

import com.scotiabank.digital.factory.adapters.out.repository.student.adapter.GetAllStudentsAdapter;
import com.scotiabank.digital.factory.application.usecase.GetAllStudentsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAllStudentsConfig {

    @Bean
    public GetAllStudentsUseCase getAllStudentsUseCase(GetAllStudentsAdapter getAllStudentsAdapter) {
        return new GetAllStudentsUseCase(getAllStudentsAdapter);
    }

}
