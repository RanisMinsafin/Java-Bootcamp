create schema if not exists shop

create table shop.product
(
    id    integer primary key,
    name  varchar(30),
    price INT
);
