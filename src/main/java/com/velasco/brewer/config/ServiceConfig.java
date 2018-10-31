package com.velasco.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.velasco.brewer.service.BeerRegisterService;
import com.velasco.brewer.storage.PhotoStorage;
import com.velasco.brewer.storage.local.PhotoStorageLocal;

@Configuration
@EnableJpaRepositories(basePackageClasses = BeerRegisterService.class)
@ComponentScan(basePackageClasses = BeerRegisterService.class)
public class ServiceConfig {
	
	@Bean
	public PhotoStorage photoStorage() {
		return new PhotoStorageLocal();
	}
	
}
