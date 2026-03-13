package com.neiry.flightcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.neiry.flightcatalog.config.TransferProperties;

@SpringBootApplication
@EnableConfigurationProperties(TransferProperties.class)

public class FlightCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightCatalogApplication.class, args);
    }
}
