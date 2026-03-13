package com.neiry.flightcatalog.flight.controller;

import com.neiry.flightcatalog.flight.dto.DirectFlightResponse;
import com.neiry.flightcatalog.flight.service.DirectFlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights/direct")
public class DirectFlightController {

    private final DirectFlightService directFlightService;

    public DirectFlightController(DirectFlightService directFlightService) {
        this.directFlightService = directFlightService;
    }

    @GetMapping
    public List<DirectFlightResponse> getAllDirectFlights() {
        return directFlightService.getAllDirectFlights();
    }

    @GetMapping("/{id}")
    public DirectFlightResponse getDirectFlightById(@PathVariable Integer id) {
        return directFlightService.getDirectFlightById(id);
    }

    @GetMapping("/search")
    public List<DirectFlightResponse> searchDirectFlights(
            @RequestParam String from,
            @RequestParam String to
    ) {
        return directFlightService.searchDirectFlights(
                from.toUpperCase(),
                to.toUpperCase()
        );
    }
}