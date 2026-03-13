package com.neiry.flightcatalog.airline.service;

import com.neiry.flightcatalog.airline.dto.AirlineResponse;

import java.util.List;

public interface AirlineService {

    List<AirlineResponse> getAll();

    AirlineResponse getByIata(String iataCode);
}