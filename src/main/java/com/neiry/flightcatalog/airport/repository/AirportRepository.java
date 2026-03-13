package com.neiry.flightcatalog.airport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neiry.flightcatalog.airport.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByIataCode(String iataCode);
}