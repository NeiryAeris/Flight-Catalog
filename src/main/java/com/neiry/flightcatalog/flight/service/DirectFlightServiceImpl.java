package com.neiry.flightcatalog.flight.service;

import com.neiry.flightcatalog.common.exception.ResourceNotFoundException;
import com.neiry.flightcatalog.flight.dto.DirectFlightResponse;
import com.neiry.flightcatalog.flight.entity.DirectFlight;
import com.neiry.flightcatalog.flight.mapper.DirectFlightMapper;
import com.neiry.flightcatalog.flight.repository.DirectFlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectFlightServiceImpl implements DirectFlightService {

    private final DirectFlightRepository directFlightRepository;
    private final DirectFlightMapper directFlightMapper;

    public DirectFlightServiceImpl(
            DirectFlightRepository directFlightRepository,
            DirectFlightMapper directFlightMapper
    ) {
        this.directFlightRepository = directFlightRepository;
        this.directFlightMapper = directFlightMapper;
    }

    @Override
    public List<DirectFlightResponse> getAllDirectFlights() {
        List<DirectFlight> flights = directFlightRepository.findByActiveTrue();
        return directFlightMapper.toResponseList(flights);
    }

    @Override
    public DirectFlightResponse getDirectFlightById(Integer id) {
        if (id == null) {
            throw new ResourceNotFoundException("Direct flight id cannot be null");
        }
        DirectFlight flight = directFlightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direct flight not found with id: " + id));

        return directFlightMapper.toResponse(flight);
    }

    @Override
    public List<DirectFlightResponse> searchDirectFlights(String from, String to) {
        List<DirectFlight> flights = directFlightRepository
                .findByFromAirport_IataCodeAndToAirport_IataCodeAndActiveTrue(from, to);

        return directFlightMapper.toResponseList(flights);
    }
}