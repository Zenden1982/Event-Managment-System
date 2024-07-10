--liquibase formatted sql


--changeset nikita:1
DROP TABLE IF EXISTS tickets;

DROP TABLE IF EXISTS events;

CREATE TABLE events(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    location_id INTEGER REFERENCES locations(id),
    category_id INTEGER REFERENCES categories(id)

);

CREATE TABLE tickets(
    id SERIAL PRIMARY KEY,
    participant_id INTEGER REFERENCES participants(id),
    event_id INTEGER REFERENCES events(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL

);

