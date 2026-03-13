package com.neiry.flightcatalog.airport.controller;

import com.neiry.flightcatalog.airport.dto.AirportResponse;
import com.neiry.flightcatalog.airport.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportResponse> getAll() {
        return airportService.getAll();
    }

    @GetMapping("/{iata}")
    public AirportResponse getByIata(@PathVariable String iata) {
        return airportService.getByIata(iata);
    }
}