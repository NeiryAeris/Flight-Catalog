package com.neiry.flightcatalog.importjob.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "import_jobs")
public class ImportJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fileName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImportStatus status;

    @Column(nullable = false)
    private Integer totalRows = 0;

    @Column(nullable = false)
    private Integer successRows = 0;

    @Column(nullable = false)
    private Integer failedRows = 0;

    @Column(length = 2000)
    private String errorMessage;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    public ImportJob() {
    }

    public Integer getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ImportStatus getStatus() {
        return status;
    }

    public void setStatus(ImportStatus status) {
        this.status = status;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getSuccessRows() {
        return successRows;
    }

    public void setSuccessRows(Integer successRows) {
        this.successRows = successRows;
    }

    public Integer getFailedRows() {
        return failedRows;
    }

    public void setFailedRows(Integer failedRows) {
        this.failedRows = failedRows;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
}