--liquibase formatted sql

--changeset nikita:1
insert into users (username, password, email, role) values ('nikita', '123', 'user2@localhost', 'ADMIN');



