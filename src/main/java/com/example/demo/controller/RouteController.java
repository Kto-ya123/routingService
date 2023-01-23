package com.example.demo.controller;

import com.example.demo.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeServiceImpl;

    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<List<String>> getRoute(@PathVariable("origin") String origin,
                                                 @PathVariable("destination") String destination) {
        List<String> result = routeServiceImpl.getRoute(origin, destination);
        if (Objects.nonNull(result)) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
