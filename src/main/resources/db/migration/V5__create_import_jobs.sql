CREATE TABLE import_jobs (
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