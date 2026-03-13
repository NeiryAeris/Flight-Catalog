package com.neiry.flightcatalog.airport.mapper;

import com.neiry.flightcatalog.airport.dto.AirportResponse;
import com.neiry.flightcatalog.airport.entity.Airport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportResponse toResponse(Airport airport);

    List<AirportResponse> toResponseList(List<Airport> airports);
}