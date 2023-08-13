CREATE TABLE doctor
(
    id            bigserial      not null,
    name          VARCHAR(30)  not null,
    email         VARCHAR(30)  not null unique,
    certification varchar(10)  not null unique,
    specialty     varchar(50)  not null,
    street        varchar(100) not null,
    crossings     varchar(100),
    num           varchar(10)  not null,
    city          varchar(30)  not null,
    country       varchar(30)  not null,
    primary key (id)
);