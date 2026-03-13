package com.neiry.flightcatalog.importjob.parser;

import com.neiry.flightcatalog.importjob.dto.FlightCsvRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightCsvParser {

    public List<FlightCsvRow> parse(MultipartFile file) throws IOException {
        List<FlightCsvRow> rows = new ArrayList<>();

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)
                );
                CSVParser csvParser = CSVFormat.DEFAULT
                        .builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .setTrim(true)
                        .build()
                        .parse(reader)
        ) {
            for (CSVRecord record : csvParser) {
                FlightCsvRow row = new FlightCsvRow();
                row.setAirlineCode(record.get("airline_code"));
                row.setFlightNumber(record.get("flight_number"));
                row.setFromAirport(record.get("from_airport"));
                row.setToAirport(record.get("to_airport"));
                row.setDepartureTime(record.get("departure_time"));
                row.setArrivalTime(record.get("arrival_time"));
                row.setOperatingDays(record.get("operating_days"));
                row.setEffectiveMonth(record.get("effective_month"));
                rows.add(row);
            }
        }

        return rows;
    }
}