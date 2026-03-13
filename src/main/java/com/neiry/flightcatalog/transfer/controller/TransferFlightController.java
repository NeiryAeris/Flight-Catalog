package com.neiry.flightcatalog.transfer.controller;

import com.neiry.flightcatalog.transfer.dto.TransferFlightResponse;
import com.neiry.flightcatalog.transfer.dto.TransferGenerationResult;
import com.neiry.flightcatalog.transfer.service.TransferFlightService;
import com.neiry.flightcatalog.transfer.service.TransferGenerationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferFlightController {

    private final TransferGenerationService transferGenerationService;
    private final TransferFlightService transferFlightService;

    public TransferFlightController(TransferGenerationService transferGenerationService,
                                    TransferFlightService transferFlightService) {
        this.transferGenerationService = transferGenerationService;
        this.transferFlightService = transferFlightService;
    }

    @PostMapping("/generate")
    public TransferGenerationResult generate(
            @RequestParam("effectiveMonth")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveMonth
    ) {
        return transferGenerationService.generateForMonth(effectiveMonth);
    }

    @GetMapping
    public List<TransferFlightResponse> getByMonth(
            @RequestParam("effectiveMonth")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate effectiveMonth
    ) {
        return transferFlightService.getByMonth(effectiveMonth);
    }
}