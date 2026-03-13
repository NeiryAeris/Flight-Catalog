package com.neiry.flightcatalog.airport.service;

import com.neiry.flightcatalog.airport.dto.AirportResponse;
import com.neiry.flightcatalog.airport.entity.Airport;
import com.neiry.flightcatalog.airport.mapper.AirportMapper;
import com.neiry.flightcatalog.airport.repository.AirportRepository;
import com.neiry.flightcatalog.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    public AirportServiceImpl(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    @Override
    public List<AirportResponse> getAll() {
        List<Airport> airports = airportRepository.findAll();
        return airportMapper.toResponseList(airports);
    }

    @Override
    public AirportResponse getByIata(String iataCode) {
        Airport airport = airportRepository.findByIataCode(iataCode.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with IATA code: " + iataCode));

        return airportMapper.toResponse(airport);
    }
}