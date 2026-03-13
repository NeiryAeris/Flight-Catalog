package com.neiry.flightcatalog.importjob.service;

import com.neiry.flightcatalog.importjob.dto.FlightImportResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FlightImportService {
    FlightImportResponse importFlights(MultipartFile file);
}