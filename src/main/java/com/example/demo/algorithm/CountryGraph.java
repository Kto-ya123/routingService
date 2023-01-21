package com.example.demo.algorithm;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryGraph {
    @Getter
    private final Map<String, List<String>> adjacencyList;

    public CountryGraph(List<Country> countries){
        this.adjacencyList = new HashMap<>();
        for (Country country : countries) {
            adjacencyList.put(country.getName(), country.getBorders());
        }
    }
}
