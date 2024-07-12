--liquibase formatted sql

--changeset nikita:1
CREATE TABLE IF NOT EXISTS user_roles  (
    user_id SERIAL REFERENCES users(id),
    role_id SERIAL REFERENCES roles(id)
)



