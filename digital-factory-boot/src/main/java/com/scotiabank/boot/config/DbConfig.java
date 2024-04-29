package com.scotiabank.boot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * Configuración de la base de datos.
 */
@Configuration
@EnableR2dbcRepositories(
        basePackages = "com.scotiabank.infrastructure.adapters.out.repository",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*ReactiveRepository")
)
@EntityScan("com.scotiabank.infrastructure.adapters.out.repository.entity")
public class DbConfig {

}
