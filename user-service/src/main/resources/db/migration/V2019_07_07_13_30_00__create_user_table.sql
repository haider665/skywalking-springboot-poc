CREATE TABLE users
(
    id   SERIAL       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT id_pk PRIMARY KEY (id)
);