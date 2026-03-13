CREATE TABLE direct_flights (
    id SERIAL PRIMARY KEY,
    airline_id SERIAL NOT NULL,
    flight_number VARCHAR(20) NOT NULL,
    from_airport_id INTEGER NOT NULL,
    to_airport_id INTEGER NOT NULL,
    departure_time TIME NOT NULL,
    arrival_time TIME NOT NULL,
    duration_minutes INTEGER NOT NULL,
    operating_days VARCHAR(100),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_direct_flights_airline
        FOREIGN KEY (airline_id) REFERENCES airlines(id),   

    CONSTRAINT fk_direct_flights_from_airport
        FOREIGN KEY (from_airport_id) REFERENCES airports(id),

    CONSTRAINT fk_direct_flights_to_airport
        FOREIGN KEY (to_airport_id) REFERENCES airports(id)
);