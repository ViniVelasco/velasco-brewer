package com.velasco.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.velasco.brewer.service.BeerRegisterService;

@Configuration
@EnableJpaRepositories(basePackageClasses = BeerRegisterService.class)
@ComponentScan(basePackageClasses = BeerRegisterService.class)
public class ServiceConfig {

}
