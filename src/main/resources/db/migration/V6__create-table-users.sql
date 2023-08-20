create table users
(
    id    bigserial   not null,
    login  VARCHAR(100) not null,
    key VARCHAR(300) not null,
    primary key (id)
)