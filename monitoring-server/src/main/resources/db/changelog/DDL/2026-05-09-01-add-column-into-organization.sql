--liquibase formatted sql

--changeset titandea:01-01-add-column-into-organization
--comment: добавление колонки контактного лица в таблицу организации
ALTER TABLE organization ADD COLUMN contact_person VARCHAR(255);

--rollback ALTER TABLE organization DROP COLUMN contact_person;