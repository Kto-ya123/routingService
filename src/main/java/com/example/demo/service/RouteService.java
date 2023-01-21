package com.example.demo.service;

import com.example.demo.algorithm.BFSAlgorithm;
import com.example.demo.algorithm.Country;
import com.example.demo.algorithm.CountryGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class RouteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);
    private static final String COUNTRIES_URI = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";
    private CountryGraph graph;

    private RestTemplate initializeRestTemplate(){
        // This converter was created just because categories.json returns text/plain instead of application/json
        // and it throws an exception
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        return new RestTemplate(messageConverters);
    }

    @PostConstruct
    public void initializeGraph(){
        RestTemplate restTemplate = initializeRestTemplate();

        LOGGER.info("Start of country graph initialization");

        Country[] countries = restTemplate.getForEntity(COUNTRIES_URI, Country[].class).getBody();
        this.graph = new CountryGraph(List.of(countries));

        LOGGER.info("End of country graph initialization");
    }

    @Nullable
    public LinkedList<String> getRoute(String originNode, String destinationNode){
        return new BFSAlgorithm(graph.getAdjacencyList()).getRoute(originNode, destinationNode);
    }
}
