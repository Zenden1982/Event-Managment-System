--liquibase formatted sql

--changeset Nikita:1
ALTER TABLE events
    ADD price BIGINT DEFAULT 0 NULL;
GO



