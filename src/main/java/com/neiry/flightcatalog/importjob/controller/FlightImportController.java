package com.neiry.flightcatalog.importjob.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neiry.flightcatalog.importjob.dto.FlightImportResponse;
import com.neiry.flightcatalog.importjob.service.FlightImportService;

@RestController
@RequestMapping("/api/import/flights")
public class FlightImportController {
    private final FlightImportService flightImportService;

    public FlightImportController(FlightImportService flightImportService) {
        this.flightImportService = flightImportService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FlightImportResponse> importFlights(@RequestParam("file") MultipartFile file) {
        FlightImportResponse response = flightImportService.importFlights(file);
        return ResponseEntity.ok(response);
    }
}
