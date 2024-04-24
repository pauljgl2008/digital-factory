package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.GetAllStudentsUseCaseImpl;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.GetAllStudentsAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAllStudentsConfig {

    @Bean
    public GetAllStudentsUseCaseImpl getAllStudentsUseCase(GetAllStudentsAdapter getAllStudentsAdapter) {
        return new GetAllStudentsUseCaseImpl(getAllStudentsAdapter);
    }

}
