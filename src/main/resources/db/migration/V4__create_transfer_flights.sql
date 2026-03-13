CREATE TABLE transfer_flights (
    id SERIAL PRIMARY KEY,
    first_leg_id INTEGER,
    second_leg_id INTEGER,
    transfer_airport_id INTEGER,
    transfer_minutes INTEGER,
    total_duration_minutes INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);