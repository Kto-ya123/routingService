package com.example.demo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CountriesIntegrationServiceConfig {
    
    @Value("${countries-integration-service.url}")
    private String countriesIntegrationServiceUrl;
}
