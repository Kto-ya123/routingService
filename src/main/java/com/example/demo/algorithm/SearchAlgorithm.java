package com.example.demo.model;

import java.util.List;

public interface SearchAlgorithm {
    List<String> getRoute(String origin, String destination);
}
