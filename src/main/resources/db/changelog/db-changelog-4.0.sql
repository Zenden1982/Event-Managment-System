--liquibase formatted sql


--changeset nikita:1
insert into users (username, password, email, role) values ('admin', '$2y$10$.LQHnhZqzNELHovUmSVaq.nGaBReANDT.5YKuAG3ly1IpqJW6nabS', 'admin@localhost', 'ADMIN');