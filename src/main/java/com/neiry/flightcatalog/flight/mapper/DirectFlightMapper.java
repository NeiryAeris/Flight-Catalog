package com.neiry.flightcatalog.flight.mapper;

import com.neiry.flightcatalog.flight.dto.DirectFlightResponse;
import com.neiry.flightcatalog.flight.entity.DirectFlight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DirectFlightMapper {

    public DirectFlightResponse toResponse(DirectFlight flight) {
        DirectFlightResponse response = new DirectFlightResponse();
        response.setId(flight.getId());
        response.setAirlineCode(flight.getAirline().getIataCode());
        response.setAirlineName(flight.getAirline().getName());
        response.setFlightNumber(flight.getFlightNumber());
        response.setFromAirportCode(flight.getFromAirport().getIataCode());
        response.setFromAirportName(flight.getFromAirport().getName());
        response.setToAirportCode(flight.getToAirport().getIataCode());
        response.setToAirportName(flight.getToAirport().getName());
        response.setDepartureTime(flight.getDepartureTime() != null ? flight.getDepartureTime().toString() : null);
        response.setArrivalTime(flight.getArrivalTime() != null ? flight.getArrivalTime().toString() : null);
        response.setDurationMinutes(flight.getDurationMinutes());
        response.setOperatingDays(flight.getOperatingDays());
        return response;
    }

    public List<DirectFlightResponse> toResponseList(List<DirectFlight> flights) {
        List<DirectFlightResponse> responses = new ArrayList<>();
        for (DirectFlight flight : flights) {
            responses.add(toResponse(flight));
        }
        return responses;
    }
}