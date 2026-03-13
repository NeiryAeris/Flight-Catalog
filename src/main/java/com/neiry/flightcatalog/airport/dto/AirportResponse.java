package com.neiry.flightcatalog.airport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportResponse {

    private String iataCode;
    private String name;
    private String city;
    private String country;
}