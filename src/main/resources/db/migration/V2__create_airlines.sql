CREATE TABLE airlines (
    id SERIAL PRIMARY KEY,
    iata_code VARCHAR(10),
    name VARCHAR(255),
    country VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);