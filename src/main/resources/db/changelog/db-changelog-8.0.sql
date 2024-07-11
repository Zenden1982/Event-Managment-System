--liquibase formatted sql

--changeset nikita:1
insert into users (id, username, password, email, role) values (9, 'bogdan', '$2y$10$pU1iTX65MJFqv3SsR1/lrebwDaYh17uxUjL09p9z0DCmxYRDFPjCm', 'kokoch@mail.ru', 'USER');

--changeset nikita:2
insert into user_roles(user_id, role_id) values (9, 2);



