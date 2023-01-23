package com.example.demo.service;

import java.util.List;

public interface RouteService {

    List<String> getRoute(String originNode, String destinationNode);
}
