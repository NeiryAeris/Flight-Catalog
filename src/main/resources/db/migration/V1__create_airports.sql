CREATE TABLE airports (
    id SERIAL PRIMARY KEY,
    iata_code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);