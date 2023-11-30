create schema if not exists shop;

create table if not exists shop.product
(
    id    integer primary key,
    name  varchar(30),
    price integer
);

create table if not exists shop.users
(
    id    integer primary key,
    login  varchar(30),
    password  varchar(30),
    authentication_status boolean
);