--liquibase formatted sql

--changeset titandea:01-01-create-organization-table
--comment: создание таблицы для организаций
CREATE TABLE organization
(
    id   UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone_number VARCHAR(255)
);
--rollback DROP TABLE organization CASCADE;