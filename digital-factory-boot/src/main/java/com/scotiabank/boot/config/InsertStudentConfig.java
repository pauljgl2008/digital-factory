package com.scotiabank.boot.config;

import com.scotiabank.application.usecase.InsertStudentUseCase;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.FindStudentByIdAdapter;
import com.scotiabank.infrastructure.adapters.out.repository.adapter.InsertStudentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración para la inserción de alumnos.
 */
@Configuration
public class InsertStudentConfig {

    /**
     * Bean para obtener el caso de uso de inserción de alumnos.
     *
     * @param findStudentByIdAdapter El adaptador para buscar alumnos por ID.
     * @param insertStudentAdapter   El adaptador para insertar alumnos.
     * @return El caso de uso para insertar alumnos.
     */
    @Bean
    public InsertStudentUseCase insertStudentUseCase(FindStudentByIdAdapter findStudentByIdAdapter,
                                                     InsertStudentAdapter insertStudentAdapter) {
        return new InsertStudentUseCase(findStudentByIdAdapter, insertStudentAdapter);
    }

}
