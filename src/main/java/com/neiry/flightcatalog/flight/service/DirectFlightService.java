package com.neiry.flightcatalog.flight.service;

import com.neiry.flightcatalog.flight.dto.DirectFlightResponse;

import java.util.List;

public interface DirectFlightService {

    List<DirectFlightResponse> getAllDirectFlights();

    DirectFlightResponse getDirectFlightById(Integer id);

    List<DirectFlightResponse> searchDirectFlights(String from, String to);
}