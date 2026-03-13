package com.neiry.flightcatalog.transfer.service;

import com.neiry.flightcatalog.common.util.OperatingDayUtils;
import com.neiry.flightcatalog.common.util.TimeUtils;
import com.neiry.flightcatalog.config.TransferProperties;
import com.neiry.flightcatalog.flight.entity.DirectFlight;
import com.neiry.flightcatalog.flight.repository.DirectFlightRepository;
import com.neiry.flightcatalog.transfer.dto.TransferGenerationResult;
import com.neiry.flightcatalog.transfer.entity.TransferFlight;
import com.neiry.flightcatalog.transfer.repository.TransferFlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransferGenerationServiceImpl implements TransferGenerationService {

    private final DirectFlightRepository directFlightRepository;
    private final TransferFlightRepository transferFlightRepository;
    private final TransferProperties transferProperties;

    public TransferGenerationServiceImpl(DirectFlightRepository directFlightRepository,
                                         TransferFlightRepository transferFlightRepository,
                                         TransferProperties transferProperties) {
        this.directFlightRepository = directFlightRepository;
        this.transferFlightRepository = transferFlightRepository;
        this.transferProperties = transferProperties;
    }

    @Override
    @Transactional
    public TransferGenerationResult generateForMonth(LocalDate effectiveMonth) {
        List<DirectFlight> directFlights = directFlightRepository.findByEffectiveMonthAndActiveTrue(effectiveMonth);

        transferFlightRepository.deleteByEffectiveMonth(effectiveMonth);

        Map<Integer, List<DirectFlight>> flightsByOriginAirport = directFlights.stream()
                .collect(Collectors.groupingBy(flight -> flight.getFromAirport().getId()));

        List<TransferFlight> generatedTransfers = new ArrayList<>();
        int skipped = 0;

        for (DirectFlight firstLeg : directFlights) {
            List<DirectFlight> secondLegCandidates =
                    flightsByOriginAirport.getOrDefault(firstLeg.getToAirport().getId(), List.of());

            for (DirectFlight secondLeg : secondLegCandidates) {
                if (!isValidConnection(firstLeg, secondLeg)) {
                    skipped++;
                    continue;
                }

                TransferFlight transferFlight = buildTransferFlight(firstLeg, secondLeg, effectiveMonth);
                generatedTransfers.add(transferFlight);
            }
        }

        transferFlightRepository.saveAll(generatedTransfers);

        return new TransferGenerationResult(
                directFlights.size(),
                generatedTransfers.size(),
                skipped
        );
    }

    private boolean isValidConnection(DirectFlight firstLeg, DirectFlight secondLeg) {
        if (firstLeg.getId().equals(secondLeg.getId())) {
            return false;
        }

        if (!firstLeg.getToAirport().getId().equals(secondLeg.getFromAirport().getId())) {
            return false;
        }

        if (firstLeg.getFromAirport().getId().equals(secondLeg.getToAirport().getId())) {
            return false;
        }

        if (!OperatingDayUtils.hasOverlap(firstLeg.getOperatingDays(), secondLeg.getOperatingDays())) {
            return false;
        }

        int transferMinutes = TimeUtils.calculateMinutesBetween(
                firstLeg.getArrivalTime(),
                secondLeg.getDepartureTime()
        );

        return transferMinutes >= transferProperties.getMinMinutes()
                && transferMinutes <= transferProperties.getMaxMinutes();
    }

    private TransferFlight buildTransferFlight(DirectFlight firstLeg, DirectFlight secondLeg, LocalDate effectiveMonth) {
        int transferMinutes = TimeUtils.calculateMinutesBetween(
                firstLeg.getArrivalTime(),
                secondLeg.getDepartureTime()
        );

        int totalDuration = firstLeg.getDurationMinutes()
                + transferMinutes
                + secondLeg.getDurationMinutes();

        String overlappingDays = OperatingDayUtils.intersect(
                firstLeg.getOperatingDays(),
                secondLeg.getOperatingDays()
        );

        TransferFlight transferFlight = new TransferFlight();
        transferFlight.setFirstLeg(firstLeg);
        transferFlight.setSecondLeg(secondLeg);
        transferFlight.setOriginAirport(firstLeg.getFromAirport());
        transferFlight.setTransferAirport(firstLeg.getToAirport());
        transferFlight.setDestinationAirport(secondLeg.getToAirport());
        transferFlight.setTransferMinutes(transferMinutes);
        transferFlight.setTotalDurationMinutes(totalDuration);
        transferFlight.setOperatingDays(overlappingDays);
        transferFlight.setEffectiveMonth(effectiveMonth);
        transferFlight.setActive(true);

        return transferFlight;
    }
}