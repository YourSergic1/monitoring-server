--liquibase formatted sql

--changeset titandea:04-01-rename-employee-to-user
--comment: переименование колонки employee_id в user_id в таблице calendar
ALTER TABLE calendar RENAME COLUMN employee_id TO user_id;

--rollback ALTER TABLE calendar RENAME COLUMN user_id TO employee_id;