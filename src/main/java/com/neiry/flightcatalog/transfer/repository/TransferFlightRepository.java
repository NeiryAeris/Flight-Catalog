package com.neiry.flightcatalog.transfer.repository;

import com.neiry.flightcatalog.transfer.entity.TransferFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransferFlightRepository extends JpaRepository<TransferFlight, Long> {

    void deleteByEffectiveMonth(LocalDate effectiveMonth);

    List<TransferFlight> findByEffectiveMonthAndActiveTrue(LocalDate effectiveMonth);
}