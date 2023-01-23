package com.example.demo.repository.impl;

import com.example.demo.config.CountriesIntegrationServiceConfig;
import com.example.demo.model.Country;
import com.example.demo.model.CountryGraph;
import com.example.demo.repository.CountryGraphIntegrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryGraphIntegrationRepositoryImpl implements CountryGraphIntegrationRepository {

    private final RestTemplate restTemplate;

    private final CountriesIntegrationServiceConfig integrationServiceConfig;

    @Override
    @Cacheable("countryGraph")
    public CountryGraph retrieve() {
        Country[] countries = restTemplate.getForEntity(integrationServiceConfig.getCountriesIntegrationServiceUrl(),
                Country[].class).getBody();

        return new CountryGraph(List.of(countries));
    }

    @CacheEvict(value = "countryGraph", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.country-graph.ttl}")
    public void clearCountryGraph() {
        log.info("Clearing country-graph cache");
    }
}
