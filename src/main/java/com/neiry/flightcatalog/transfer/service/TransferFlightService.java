package com.neiry.flightcatalog.transfer.service;

import com.neiry.flightcatalog.transfer.dto.TransferFlightResponse;

import java.time.LocalDate;
import java.util.List;

public interface TransferFlightService {
    List<TransferFlightResponse> getByMonth(LocalDate effectiveMonth);
}