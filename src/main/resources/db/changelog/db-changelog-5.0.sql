--liquibase formatted sql



--changeset nikita:2
insert into users (username, password, email, role) values ('user', '$2y$10$hLcoRzOBjryR1YW6nIVs..FMDJAtw2K.4NhMFQgBRXA84cX/aWi/q', 'user@localhost', 'USER');
