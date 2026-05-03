--liquibase formatted sql

--changeset titandea:01-02-create-agent-table
--comment: создание таблицы для агентов
CREATE TABLE agent
(
    id                   UUID PRIMARY KEY,
    local_ip             VARCHAR(32) NOT NULL,
    organization_id      UUID        NOT NULL REFERENCES organization (id) ON DELETE CASCADE,
    continuous           BOOLEAN     NOT NULL,
    start_time           TIME,
    end_time             TIME,
    last_metric_received TIMESTAMP
);
--rollback DROP TABLE agent CASCADE;