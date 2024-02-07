package com.example.security20.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.security20.repository")
public class JpaConfig {
    // Дополнительная конфигурация JPA, если необходимо
}