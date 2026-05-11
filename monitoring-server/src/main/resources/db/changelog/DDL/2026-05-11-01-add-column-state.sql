--liquibase formatted sql

--changeset titandea:01-01-add-column-into-organization
--comment: добавление колонки контактного лица в таблицу организации
ALTER TABLE organization ADD COLUMN state VARCHAR(32);

--rollback ALTER TABLE organization DROP COLUMN state;

--changeset titandea:01-02-add-column-into-agent
--comment: добавление колонки контактного лица в таблицу организации
ALTER TABLE agent ADD COLUMN state VARCHAR(32);

--rollback ALTER TABLE agent DROP COLUMN state;