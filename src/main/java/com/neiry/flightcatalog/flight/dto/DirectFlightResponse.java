package com.neiry.flightcatalog.flight.dto;

public class DirectFlightResponse {

    private Integer id;
    private String airlineCode;
    private String airlineName;
    private String flightNumber;
    private String fromAirportCode;
    private String fromAirportName;
    private String toAirportCode;
    private String toAirportName;
    private String departureTime;
    private String arrivalTime;
    private Integer durationMinutes;
    private String operatingDays;

    public DirectFlightResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public void setFromAirportCode(String fromAirportCode) {
        this.fromAirportCode = fromAirportCode;
    }

    public String getFromAirportName() {
        return fromAirportName;
    }

    public void setFromAirportName(String fromAirportName) {
        this.fromAirportName = fromAirportName;
    }

    public String getToAirportCode() {
        return toAirportCode;
    }

    public void setToAirportCode(String toAirportCode) {
        this.toAirportCode = toAirportCode;
    }

    public String getToAirportName() {
        return toAirportName;
    }

    public void setToAirportName(String toAirportName) {
        this.toAirportName = toAirportName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
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
}