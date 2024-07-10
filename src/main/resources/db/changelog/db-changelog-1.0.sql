--liquibase formatted sql

--changeset nikita:1
CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role VARCHAR(255)
);


CREATE TABLE locations(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    capacity INTEGER
);


CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE participants(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE events(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    date TIMESTAMP NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL,
    location_id INTEGER REFERENCES locations(id),
    category_id INTEGER REFERENCES categories(id)

);


CREATE TABLE tickets(
    id SERIAL PRIMARY KEY,
    participant_id INTEGER REFERENCES participants(id),
    event_id INTEGER REFERENCES events(id),
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP NOT NULL

);