package com.neiry.flightcatalog.importjob.service;

import com.neiry.flightcatalog.airline.entity.Airline;
import com.neiry.flightcatalog.airline.repository.AirlineRepository;
import com.neiry.flightcatalog.airport.entity.Airport;
import com.neiry.flightcatalog.airport.repository.AirportRepository;
import com.neiry.flightcatalog.flight.entity.DirectFlight;
import com.neiry.flightcatalog.flight.repository.DirectFlightRepository;
import com.neiry.flightcatalog.importjob.dto.FlightCsvRow;
import com.neiry.flightcatalog.importjob.dto.FlightImportResponse;
import com.neiry.flightcatalog.importjob.entity.ImportJob;
import com.neiry.flightcatalog.importjob.entity.ImportStatus;
import com.neiry.flightcatalog.importjob.parser.FlightCsvParser;
import com.neiry.flightcatalog.importjob.repository.ImportJobRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightImportServiceImpl implements FlightImportService {

    private final FlightCsvParser flightCsvParser;
    private final ImportJobRepository importJobRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;
    private final DirectFlightRepository directFlightRepository;

    public FlightImportServiceImpl(
            FlightCsvParser flightCsvParser,
            ImportJobRepository importJobRepository,
            AirlineRepository airlineRepository,
            AirportRepository airportRepository,
            DirectFlightRepository directFlightRepository) {
        this.flightCsvParser = flightCsvParser;
        this.importJobRepository = importJobRepository;
        this.airlineRepository = airlineRepository;
        this.airportRepository = airportRepository;
        this.directFlightRepository = directFlightRepository;
    }

    @Override
    @Transactional
    public FlightImportResponse importFlights(MultipartFile file) {
        ImportJob job = new ImportJob();
        job.setFileName(file.getOriginalFilename());
        job.setStatus(ImportStatus.PROCESSING);
        job.setStartedAt(LocalDateTime.now());
        importJobRepository.save(job);

        try {
            List<FlightCsvRow> rows = flightCsvParser.parse(file);
            job.setTotalRows(rows.size());

            if (rows.isEmpty()) {
                throw new IllegalArgumentException("CSV file is empty.");
            }

            LocalDate effectiveMonth = LocalDate.parse(rows.get(0).getEffectiveMonth() + "-01");

            directFlightRepository.deleteByEffectiveMonth(effectiveMonth);

            int successCount = 0;
            int failedCount = 0;

            for (FlightCsvRow row : rows) {
                try {
                    validateRow(row);

                    Airline airline = airlineRepository.findByIataCode(row.getAirlineCode())
                            .orElseThrow(
                                    () -> new IllegalArgumentException("Airline not found: " + row.getAirlineCode()));

                    Airport fromAirport = airportRepository.findByIataCode(row.getFromAirport())
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "From airport not found: " + row.getFromAirport()));

                    Airport toAirport = airportRepository.findByIataCode(row.getToAirport())
                            .orElseThrow(
                                    () -> new IllegalArgumentException("To airport not found: " + row.getToAirport()));

                    LocalTime departureTime = LocalTime.parse(row.getDepartureTime());
                    LocalTime arrivalTime = LocalTime.parse(row.getArrivalTime());

                    int durationMinutes = calculateDurationMinutes(departureTime, arrivalTime);

                    DirectFlight flight = new DirectFlight();
                    flight.setAirline(airline);
                    flight.setFlightNumber(row.getFlightNumber());
                    flight.setFromAirport(fromAirport);
                    flight.setToAirport(toAirport);
                    flight.setDepartureTime(departureTime);
                    flight.setArrivalTime(arrivalTime);
                    flight.setDurationMinutes(durationMinutes);
                    flight.setOperatingDays(row.getOperatingDays());
                    flight.setEffectiveMonth(effectiveMonth);

                    directFlightRepository.save(flight);
                    successCount++;

                } catch (Exception e) {
                    failedCount++;
                    System.out.println("Failed row: " + row.getFlightNumber() + " | reason: " + e.getMessage());
                    System.err.println("""
                            Failed row:
                            airline=%s, flight=%s, from=%s, to=%s, dep=%s, arr=%s, month=%s
                            reason=%s
                            """.formatted(
                            row.getAirlineCode(),
                            row.getFlightNumber(),
                            row.getFromAirport(),
                            row.getToAirport(),
                            row.getDepartureTime(),
                            row.getArrivalTime(),
                            row.getEffectiveMonth(),
                            e.getMessage()));
                }
            }

            job.setSuccessRows(successCount);
            job.setFailedRows(failedCount);
            job.setStatus(ImportStatus.COMPLETED);
            job.setFinishedAt(LocalDateTime.now());
            importJobRepository.save(job);

            return new FlightImportResponse(
                    job.getId(),
                    job.getStatus().name(),
                    job.getTotalRows(),
                    job.getSuccessRows(),
                    job.getFailedRows(),
                    "Flight CSV imported successfully.");

        } catch (Exception e) {
            job.setStatus(ImportStatus.FAILED);
            job.setErrorMessage(e.getMessage());
            job.setFinishedAt(LocalDateTime.now());
            importJobRepository.save(job);

            return new FlightImportResponse(
                    job.getId(),
                    job.getStatus().name(),
                    job.getTotalRows(),
                    job.getSuccessRows(),
                    job.getFailedRows(),
                    "Import failed: " + e.getMessage());
        }
    }

    private void validateRow(FlightCsvRow row) {
        if (isBlank(row.getAirlineCode()))
            throw new IllegalArgumentException("airline_code is required");
        if (isBlank(row.getFlightNumber()))
            throw new IllegalArgumentException("flight_number is required");
        if (isBlank(row.getFromAirport()))
            throw new IllegalArgumentException("from_airport is required");
        if (isBlank(row.getToAirport()))
            throw new IllegalArgumentException("to_airport is required");
        if (isBlank(row.getDepartureTime()))
            throw new IllegalArgumentException("departure_time is required");
        if (isBlank(row.getArrivalTime()))
            throw new IllegalArgumentException("arrival_time is required");
        if (isBlank(row.getOperatingDays()))
            throw new IllegalArgumentException("operating_days is required");
        if (isBlank(row.getEffectiveMonth()))
            throw new IllegalArgumentException("effective_month is required");

        if (row.getFromAirport().equalsIgnoreCase(row.getToAirport())) {
            throw new IllegalArgumentException("from_airport and to_airport cannot be the same");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private int calculateDurationMinutes(LocalTime departure, LocalTime arrival) {
        if (!arrival.isBefore(departure)) {
            return (int) Duration.between(departure, arrival).toMinutes();
        }
        return (int) Duration.between(departure, arrival.plusHours(24)).toMinutes();
    }
}