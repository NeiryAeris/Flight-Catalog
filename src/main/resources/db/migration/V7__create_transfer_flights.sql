CREATE TABLE transfer_flights (
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