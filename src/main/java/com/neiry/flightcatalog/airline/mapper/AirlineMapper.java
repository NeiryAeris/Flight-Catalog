package com.neiry.flightcatalog.airline.mapper;

import com.neiry.flightcatalog.airline.dto.AirlineResponse;
import com.neiry.flightcatalog.airline.entity.Airline;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    AirlineResponse toResponse(Airline airline);

    List<AirlineResponse> toResponseList(List<Airline> airlines);
}