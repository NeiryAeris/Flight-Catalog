package com.neiry.flightcatalog.importjob.dto;

public class FlightImportResponse {

    private Integer importJobId;
    private String status;
    private Integer totalRows;
    private Integer successRows;
    private Integer failedRows;
    private String message;

    public FlightImportResponse() {
    }

    public FlightImportResponse(Integer importJobId, String status, Integer totalRows, Integer successRows, Integer failedRows, String message) {
        this.importJobId = importJobId;
        this.status = status;
        this.totalRows = totalRows;
        this.successRows = successRows;
        this.failedRows = failedRows;
        this.message = message;
    }

    public Integer getImportJobId() {
        return importJobId;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public Integer getSuccessRows() {
        return successRows;
    }

    public Integer getFailedRows() {
        return failedRows;
    }

    public String getMessage() {
        return message;
    }
}