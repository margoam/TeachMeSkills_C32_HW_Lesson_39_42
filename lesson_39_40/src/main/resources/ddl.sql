-- we don't know how to generate root <with-no-name> (class Root) :(

comment on database postgres is 'default administrative connection database';

create table users
(
    id           bigserial
        constraint id
            primary key,
    firstname    varchar(50),
    secondname   varchar(50),
    age          integer,
    email        varchar(50),
    sex          varchar(10),
    mobile_phone varchar(20),
    is_deleted   boolean,
    created      timestamp default now() not null,
    updated      timestamp
);

alter table users
    owner to user1;

create table security
(
    id       bigserial
        constraint security_id
            primary key
        constraint user_id
            references users,
    login    varchar(50),
    password varchar(50),
    role     varchar(20),
    created  timestamp default now() not null,
    updated  timestamp,
    user_id  bigserial
);

alter table security
    owner to user1;

create table product
(
    id      bigserial
        constraint product_id
            primary key,
    name    varchar(50),
    price   double precision,
    created timestamp,
    updated timestamp
);

alter table product
    owner to user1;

create table users_product
(
    id         bigserial
        primary key,
    user_id    bigserial
        constraint user_id
            references users,
    product_id bigserial
        constraint product_id
            references product,
    created    time,
    updated    timestamp
);

alter table users_product
    owner to user1;

