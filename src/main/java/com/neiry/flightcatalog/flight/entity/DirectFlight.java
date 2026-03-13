package com.neiry.flightcatalog.flight.entity;

import com.neiry.flightcatalog.airline.entity.Airline;
import com.neiry.flightcatalog.airport.entity.Airport;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "direct_flights")
public class DirectFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @Column(name = "flight_number", nullable = false, length = 20)
    private String flightNumber;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_airport_id", nullable = false)
    private Airport fromAirport;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_airport_id", nullable = false)
    private Airport toAirport;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(name = "operating_days", length = 100)
    private String operatingDays;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "effective_month")
    private LocalDate effectiveMonth;

    public DirectFlight() {
    }

    public Integer getId() {
        return id;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airport getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    public Airport getToAirport() {
        return toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getOperatingDays() {
        return operatingDays;
    }

    public void setOperatingDays(String operatingDays) {
        this.operatingDays = operatingDays;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setEffectiveMonth(LocalDate effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }
}