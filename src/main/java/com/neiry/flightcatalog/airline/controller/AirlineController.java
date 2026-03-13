package com.neiry.flightcatalog.airline.controller;

import com.neiry.flightcatalog.airline.dto.AirlineResponse;
import com.neiry.flightcatalog.airline.service.AirlineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<AirlineResponse> getAll() {
        return airlineService.getAll();
    }

    @GetMapping("/{iata}")
    public AirlineResponse getByIata(@PathVariable String iata) {
        return airlineService.getByIata(iata);
    }
}