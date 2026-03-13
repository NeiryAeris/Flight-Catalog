package com.neiry.flightcatalog.flight.repository;

import com.neiry.flightcatalog.flight.entity.DirectFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DirectFlightRepository extends JpaRepository<DirectFlight, Integer> {

    List<DirectFlight> findByFromAirport_IataCodeAndToAirport_IataCodeAndActiveTrue(
            String fromIataCode,
            String toIataCode
    );

    List<DirectFlight> findByActiveTrue();

    @Modifying
    @Query("delete from DirectFlight d where d.effectiveMonth = :effectiveMonth")
    void deleteByEffectiveMonth(LocalDate effectiveMonth);
    
    List<DirectFlight> findByEffectiveMonthAndActiveTrue(LocalDate effectiveMonth);
}