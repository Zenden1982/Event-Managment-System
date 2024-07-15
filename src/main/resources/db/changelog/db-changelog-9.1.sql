--liquibase formatted sql

--changeset Nikita:1
ALTER TABLE events
    DROP COLUMN price;
GO


