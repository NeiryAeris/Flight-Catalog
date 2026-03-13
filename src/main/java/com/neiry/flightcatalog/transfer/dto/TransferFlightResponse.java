package com.neiry.flightcatalog.transfer.dto;

public class TransferFlightResponse {

    private Integer id;
    private String fromAirport;
    private String transferAirport;
    private String toAirport;
    private String firstFlightNumber;
    private String secondFlightNumber;
    private Integer transferMinutes;
    private Integer totalDurationMinutes;
    private String operatingDays;

    public TransferFlightResponse() {
    }

    public TransferFlightResponse(Integer id, String fromAirport, String transferAirport, String toAirport,
                                  String firstFlightNumber, String secondFlightNumber,
                                  Integer transferMinutes, Integer totalDurationMinutes, String operatingDays) {
        this.id = id;
        this.fromAirport = fromAirport;
        this.transferAirport = transferAirport;
        this.toAirport = toAirport;
        this.firstFlightNumber = firstFlightNumber;
        this.secondFlightNumber = secondFlightNumber;
        this.transferMinutes = transferMinutes;
        this.totalDurationMinutes = totalDurationMinutes;
        this.operatingDays = operatingDays;
    }

    public Integer getId() {
        return id;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getTransferAirport() {
        return transferAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public String getFirstFlightNumber() {
        return firstFlightNumber;
    }

    public String getSecondFlightNumber() {
        return secondFlightNumber;
    }

    public Integer getTransferMinutes() {
        return transferMinutes;
    }

    public Integer getTotalDurationMinutes() {
        return totalDurationMinutes;
    }

    public String getOperatingDays() {
        return operatingDays;
    }
}