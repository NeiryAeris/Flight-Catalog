package com.neiry.flightcatalog.transfer.entity;

import com.neiry.flightcatalog.airport.entity.Airport;
import com.neiry.flightcatalog.flight.entity.DirectFlight;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_flights")
public class TransferFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "first_leg_id", nullable = false)
    private DirectFlight firstLeg;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "second_leg_id", nullable = false)
    private DirectFlight secondLeg;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport originAirport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transfer_airport_id", nullable = false)
    private Airport transferAirport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport;

    @Column(name = "total_duration_minutes", nullable = false)
    private Integer totalDurationMinutes;

    @Column(name = "transfer_minutes", nullable = false)
    private Integer transferMinutes;

    @Column(name = "operating_days", nullable = false, length = 50)
    private String operatingDays;

    @Column(name = "effective_month", nullable = false)
    private LocalDate effectiveMonth;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public DirectFlight getFirstLeg() {
        return firstLeg;
    }

    public void setFirstLeg(DirectFlight firstLeg) {
        this.firstLeg = firstLeg;
    }

    public DirectFlight getSecondLeg() {
        return secondLeg;
    }

    public void setSecondLeg(DirectFlight secondLeg) {
        this.secondLeg = secondLeg;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getTransferAirport() {
        return transferAirport;
    }

    public void setTransferAirport(Airport transferAirport) {
        this.transferAirport = transferAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Integer getTotalDurationMinutes() {
        return totalDurationMinutes;
    }

    public void setTotalDurationMinutes(Integer totalDurationMinutes) {
        this.totalDurationMinutes = totalDurationMinutes;
    }

    public Integer getTransferMinutes() {
        return transferMinutes;
    }

    public void setTransferMinutes(Integer transferMinutes) {
        this.transferMinutes = transferMinutes;
    }

    public String getOperatingDays() {
        return operatingDays;
    }

    public void setOperatingDays(String operatingDays) {
        this.operatingDays = operatingDays;
    }

    public LocalDate getEffectiveMonth() {
        return effectiveMonth;
    }

    public void setEffectiveMonth(LocalDate effectiveMonth) {
        this.effectiveMonth = effectiveMonth;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}