--liquibase formatted sql

--changeset nikita:1
CREATE TABLE IF NOT EXISTS roles  (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);




