package com.neiry.flightcatalog.airport.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "airports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String iataCode;
    private String name;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
}
