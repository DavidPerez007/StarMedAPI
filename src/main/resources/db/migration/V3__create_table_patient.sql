CREATE TABLE patient
(
    id            bigserial      not null,
    name          VARCHAR(30)  not null,
    lastname varchar(10)  not null,
    email         VARCHAR(30)  not null unique,
    age smallint not null,
    gender varchar(10) not null,
    street        varchar(100) not null,
    crossings     varchar(100),
    num           varchar(10)  not null,
    city          varchar(30)  not null,
    country       varchar(30)  not null,
    primary key (id)
);