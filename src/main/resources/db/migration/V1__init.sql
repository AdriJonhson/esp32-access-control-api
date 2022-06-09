CREATE TABLE users (
    id SERIAL primary key,
    cpf varchar(11) NOT NULL UNIQUE,
    name varchar(255) NOT NULL,
    email varchar(100) NOT NULL,
    status varchar(20) default 'ACTIVE',
    rfid_code varchar(255),
    last_access timestamp,
    creation_date timestamp,
    expire_date timestamp
);