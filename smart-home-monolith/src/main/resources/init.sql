DROP SCHEMA IF EXISTS monolith CASCADE;
CREATE SCHEMA monolith;

ALTER SCHEMA monolith OWNER TO postgres;

CREATE TABLE IF NOT EXISTS monolith.heating_systems (
    id BIGSERIAL PRIMARY KEY,
    is_on BOOLEAN NOT NULL,
    target_temperature DOUBLE PRECISION NOT NULL,
    current_temperature DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS monolith.temperature_sensors (
    id BIGSERIAL PRIMARY KEY,
    current_temperature DOUBLE PRECISION NOT NULL,
    last_updated TIMESTAMP NOT NULL
);

INSERT INTO monolith.heating_systems (is_on, target_temperature, current_temperature)
VALUES(true, 25.5, 24.0);
