package com.scotiabank.boot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.scotiabank.infrastructure.adapters.out.repository",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*ReactiveCrudRepository"))
@EntityScan("com.scotiabanxk.infrastructure.adapters.out.repository.entity")
public class DbConfig {

}