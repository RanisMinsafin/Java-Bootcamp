create schema if not exists shop;

create table if not exists shop.product
(
    id    integer primary key,
    name  varchar(30),
    price integer
);
