package com.neiry.flightcatalog.transfer.service;

import com.neiry.flightcatalog.transfer.dto.TransferFlightResponse;
import com.neiry.flightcatalog.transfer.entity.TransferFlight;
import com.neiry.flightcatalog.transfer.repository.TransferFlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferFlightServiceImpl implements TransferFlightService {

    private final TransferFlightRepository transferFlightRepository;

    public TransferFlightServiceImpl(TransferFlightRepository transferFlightRepository) {
        this.transferFlightRepository = transferFlightRepository;
    }

    @Override
    public List<TransferFlightResponse> getByMonth(LocalDate effectiveMonth) {
        return transferFlightRepository.findByEffectiveMonthAndActiveTrue(effectiveMonth)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TransferFlightResponse toResponse(TransferFlight transferFlight) {
        return new TransferFlightResponse(
                transferFlight.getId(),
                transferFlight.getOriginAirport().getIataCode(),
                transferFlight.getTransferAirport().getIataCode(),
                transferFlight.getDestinationAirport().getIataCode(),
                transferFlight.getFirstLeg().getFlightNumber(),
                transferFlight.getSecondLeg().getFlightNumber(),
                transferFlight.getTransferMinutes(),
                transferFlight.getTotalDurationMinutes(),
                transferFlight.getOperatingDays()
        );
    }
}