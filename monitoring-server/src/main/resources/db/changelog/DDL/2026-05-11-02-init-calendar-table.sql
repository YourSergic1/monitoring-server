--liquibase formatted sql

--changeset titandea:02-01-create-calendar-table
--comment: создание таблицы календаря
CREATE TABLE calendar
(
    id             UUID PRIMARY KEY,
    date           DATE NOT NULL,
    day_of_week    VARCHAR(20) NOT NULL,
    is_working_day BOOLEAN NOT NULL,
    employee_id    UUID,
    CONSTRAINT uk_calendar_date UNIQUE (date)
);
--rollback DROP TABLE calendar CASCADE;