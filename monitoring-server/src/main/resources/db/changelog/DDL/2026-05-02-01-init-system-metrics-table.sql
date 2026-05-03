--liquibase formatted sql

--changeset titandea:01-01-create-system-metrics-tables
--comment: создание таблицы системных метрик
CREATE TABLE system_metrics
(
    id             UUID PRIMARY KEY,
    agent_id       UUID        NOT NULL REFERENCES agent (id) ON DELETE CASCADE,
    hostname       VARCHAR(255),
    local_ip       VARCHAR(32) NOT NULL,
    public_ip      VARCHAR(32),
    date_time      TIMESTAMPTZ NOT NULL,
    uptime_minutes BIGINT,
    CONSTRAINT uq_system_ip_time UNIQUE (local_ip, date_time)
);
--rollback DROP TABLE system_metrics CASCADE;