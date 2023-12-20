drop schema if exists service cascade;
create schema if not exists service;

create table service.users
(
    id       serial primary key,
    email    varchar(50) unique,
    password varchar(50) unique
);