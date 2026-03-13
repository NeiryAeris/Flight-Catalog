package com.neiry.flightcatalog.transfer.service;

import com.neiry.flightcatalog.transfer.dto.TransferGenerationResult;

import java.time.LocalDate;

public interface TransferGenerationService {
    TransferGenerationResult generateForMonth(LocalDate effectiveMonth);
}