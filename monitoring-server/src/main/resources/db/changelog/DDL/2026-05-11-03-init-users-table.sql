--liquibase formatted sql

--changeset titandea:03-01-create-users-table
--comment: создание таблицы пользователей
CREATE TABLE users
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(255) NOT NULL,
    surname    VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    phone       VARCHAR(50)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP        DEFAULT NOW(),
    updated_at TIMESTAMP        DEFAULT NOW(),
    CONSTRAINT uk_users_email UNIQUE (email)
);

--rollback DROP TABLE users CASCADE;