--liquibase formatted sql

--changeset nikita:1
insert into user_roles(user_id, role_id) values (1, 1);


--changeset nikita:2
insert into user_roles(user_id, role_id) values (2, 1);
