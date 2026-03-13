package com.neiry.flightcatalog.transfer.dto;

public class TransferGenerationResult {

    private int sourceDirectFlights;
    private int generatedTransferFlights;
    private int skippedInvalidConnections;

    public TransferGenerationResult() {
    }

    public TransferGenerationResult(int sourceDirectFlights, int generatedTransferFlights, int skippedInvalidConnections) {
        this.sourceDirectFlights = sourceDirectFlights;
        this.generatedTransferFlights = generatedTransferFlights;
        this.skippedInvalidConnections = skippedInvalidConnections;
    }

    public int getSourceDirectFlights() {
        return sourceDirectFlights;
    }

    public void setSourceDirectFlights(int sourceDirectFlights) {
        this.sourceDirectFlights = sourceDirectFlights;
    }

    public int getGeneratedTransferFlights() {
        return generatedTransferFlights;
    }

    public void setGeneratedTransferFlights(int generatedTransferFlights) {
        this.generatedTransferFlights = generatedTransferFlights;
    }

    public int getSkippedInvalidConnections() {
        return skippedInvalidConnections;
    }

    public void setSkippedInvalidConnections(int skippedInvalidConnections) {
        this.skippedInvalidConnections = skippedInvalidConnections;
    }
}