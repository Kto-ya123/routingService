package com.example.demo.service;

import com.example.demo.algorithm.BFSAlgorithm;
import com.example.demo.model.CountryGraph;
import com.example.demo.repository.CountryGraphIntegrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    
    private final CountryGraphIntegrationRepository graphIntegrationRepository;

    @Override
    @Nullable
    public List<String> getRoute(String originNode, String destinationNode){
        CountryGraph countryGraph = graphIntegrationRepository.retrieve();
        return new BFSAlgorithm(countryGraph.getAdjacencyList()).getRoute(originNode, destinationNode);
    }
}
