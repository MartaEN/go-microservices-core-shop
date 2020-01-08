package com.marta.sandbox.microservices.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = {"com.marta.sandbox.microservices.shop.persistence.repository"})
@EnableTransactionManagement
public class PersistenceConfig {
}
