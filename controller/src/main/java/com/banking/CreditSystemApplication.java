package com.banking;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.banking.entities")
@EnableJpaRepositories(basePackages = "com.banking.repositories")
@ComponentScan(basePackages = {
    "com.banking.controller",
    "com.banking.business",
    "com.banking.core",
    "com.banking"
})
@OpenAPIDefinition(
    info = @Info(
        title = "Banking System API",
        version = "1.0",
        description = "Banking System REST API Documentation"
    )
)
public class CreditSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreditSystemApplication.class, args);
    }
} 