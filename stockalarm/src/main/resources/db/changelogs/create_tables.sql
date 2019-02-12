CREATE SCHEMA IF NOT EXISTS soa AUTHORIZATION postgres;

SET SCHEMA 'soa';

CREATE TABLE IF NOT EXISTS soa_user
(
   id SERIAL PRIMARY KEY NOT NULL,
   userid CHARACTER VARYING(16) NOT NULL,
   firstname CHARACTER VARYING(16),
   lastname CHARACTER VARYING(16),
   password CHARACTER VARYING(8) NOT NULL,
   email CHARACTER VARYING(16)
);

CREATE TABLE IF NOT EXISTS soa_alarm
(
   id SERIAL PRIMARY KEY NOT NULL,
   soa_user_id INTEGER REFERENCES soa_user(id),
   stockid CHARACTER VARYING(16)  NOT NULL,
   initial_value INTEGER,
   increase_percentage INTEGER,
   decrease_percentage INTEGER
);

CREATE UNIQUE INDEX IF NOT EXISTS uq_soa_user
    ON soa_user
    USING BTREE
    (userid, password COLLATE pg_catalog."default");

CREATE UNIQUE INDEX IF NOT EXISTS uq_soa_alarm
    ON soa_alarm
    USING BTREE
    (soa_user_id, stockid COLLATE pg_catalog."default");
