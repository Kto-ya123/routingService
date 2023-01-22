package com.example.demo.service;

import com.example.demo.algorithm.BFSAlgorithm;
import com.example.demo.model.Country;
import com.example.demo.model.CountryGraph;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);
    @Value("${countries.data.url}")
    public String countriesDataUrl;

    private final RestTemplate restTemplate;
    private CountryGraph graph;

    @PostConstruct
    public void initializeGraph(){
        LOGGER.info("Start of country graph initialization");

        Country[] countries = restTemplate.getForEntity(countriesDataUrl, Country[].class).getBody();
        this.graph = new CountryGraph(List.of(countries));

        LOGGER.info("End of country graph initialization");
    }

    @Nullable
    public LinkedList<String> getRoute(String originNode, String destinationNode){
        return new BFSAlgorithm(graph.getAdjacencyList()).getRoute(originNode, destinationNode);
    }
}
