package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.GetAllStudentsUseCase;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.GetAllStudentsAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class GetAllStudentsConfig {

    @Bean
    public GetAllStudentsUseCase getAllStudentsUseCase(GetAllStudentsAdapter getAllStudentsAdapter) {
        return new GetAllStudentsUseCase(getAllStudentsAdapter);
    }

}
