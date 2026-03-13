package com.neiry.flightcatalog.airline.service;

import com.neiry.flightcatalog.airline.dto.AirlineResponse;
import com.neiry.flightcatalog.airline.entity.Airline;
import com.neiry.flightcatalog.airline.mapper.AirlineMapper;
import com.neiry.flightcatalog.airline.repository.AirlineRepository;
import com.neiry.flightcatalog.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    public AirlineServiceImpl(AirlineRepository airlineRepository, AirlineMapper airlineMapper) {
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
    }

    @Override
    public List<AirlineResponse> getAll() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlineMapper.toResponseList(airlines);
    }

    @Override
    public AirlineResponse getByIata(String iataCode) {
        Airline airline = airlineRepository.findByIataCode(iataCode.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Airline not found with IATA code: " + iataCode));

        return airlineMapper.toResponse(airline);
    }
}