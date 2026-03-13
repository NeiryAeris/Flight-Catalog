CREATE TABLE IF NOT EXISTS airlines (
    id SERIAL PRIMARY KEY,
    iata_code VARCHAR(10),
    name VARCHAR(255),
    country VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS airports (
    id SERIAL PRIMARY KEY,
    iata_code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS direct_flights (
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
    effective_month DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_direct_flights_airline
        FOREIGN KEY (airline_id) REFERENCES airlines(id),   

    CONSTRAINT fk_direct_flights_from_airport
        FOREIGN KEY (from_airport_id) REFERENCES airports(id),

    CONSTRAINT fk_direct_flights_to_airport
        FOREIGN KEY (to_airport_id) REFERENCES airports(id)
);

CREATE TABLE IF NOT EXISTS import_jobs (
    id SERIAL PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    total_rows INT NOT NULL DEFAULT 0,
    success_rows INT NOT NULL DEFAULT 0,
    failed_rows INT NOT NULL DEFAULT 0,
    error_message VARCHAR(2000),
    started_at TIMESTAMP NOT NULL,
    finished_at TIMESTAMP
);
CREATE TABLE IF NOT EXISTS transfer_flights (
    id SERIAL PRIMARY KEY,
    first_leg_id BIGINT NOT NULL,
    second_leg_id BIGINT NOT NULL,
    origin_airport_id BIGINT NOT NULL,
    transfer_airport_id BIGINT NOT NULL,
    destination_airport_id BIGINT NOT NULL,
    total_duration_minutes INT NOT NULL,
    transfer_minutes INT NOT NULL,
    operating_days VARCHAR(50) NOT NULL,
    effective_month DATE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_transfer_first_leg
        FOREIGN KEY (first_leg_id) REFERENCES direct_flights(id),

    CONSTRAINT fk_transfer_second_leg
        FOREIGN KEY (second_leg_id) REFERENCES direct_flights(id),

    CONSTRAINT fk_transfer_origin_airport
        FOREIGN KEY (origin_airport_id) REFERENCES airports(id),

    CONSTRAINT fk_transfer_airport
        FOREIGN KEY (transfer_airport_id) REFERENCES airports(id),

    CONSTRAINT fk_transfer_destination_airport
        FOREIGN KEY (destination_airport_id) REFERENCES airports(id)
);

CREATE INDEX idx_transfer_effective_month
    ON transfer_flights(effective_month);

CREATE INDEX idx_transfer_origin_destination
    ON transfer_flights(origin_airport_id, destination_airport_id);

CREATE INDEX idx_transfer_transfer_airport
    ON transfer_flights(transfer_airport_id);
    