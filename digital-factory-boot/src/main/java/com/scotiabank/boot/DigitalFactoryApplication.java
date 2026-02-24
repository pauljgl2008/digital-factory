package com.scotiabank.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.scotiabank")
public class DigitalFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalFactoryApplication.class, args);
    }

}
