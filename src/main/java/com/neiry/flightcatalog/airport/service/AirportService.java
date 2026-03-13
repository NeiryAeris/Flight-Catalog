package com.neiry.flightcatalog.airport.service;

import com.neiry.flightcatalog.airport.dto.AirportResponse;

import java.util.List;

public interface AirportService {

    List<AirportResponse> getAll();

    AirportResponse getByIata(String iataCode);
}