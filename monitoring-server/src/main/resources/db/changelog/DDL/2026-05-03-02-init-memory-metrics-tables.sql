--liquibase formatted sql

--changeset titandea:02-01-create-memory-metrics-tables
--comment: создание таблицы метрик памяти
CREATE TABLE memory_metrics(
    id               UUID PRIMARY KEY ,
    system_id        UUID NOT NULL REFERENCES system_metrics (id) ON DELETE CASCADE,
    total_bytes      BIGINT,
    available_bytes  BIGINT,
    used_bytes       BIGINT,
    swap_total_bytes BIGINT,
    swap_used_bytes  BIGINT,
    swap_usage_percent DOUBLE PRECISION
);
CREATE INDEX idx_memory_system ON memory_metrics (system_id);
--rollback DROP TABLE memory_metrics CASCADE;