--liquibase formatted sql

--changeset titandea:03-01-create-disk-metrics-tables
--comment: создание таблицы метрик диска
CREATE TABLE disk_metrics
(
    id            UUID PRIMARY KEY,
    system_id     UUID NOT NULL REFERENCES system_metrics (id) ON DELETE CASCADE,
    mount_point   VARCHAR(255),
    type          VARCHAR(255),
    total_bytes   BIGINT,
    used_bytes    BIGINT,
    free_bytes    BIGINT,
    usage_percent DOUBLE PRECISION
);
CREATE INDEX idx_disk_system ON disk_metrics (system_id);
--rollback DROP TABLE disk_metrics CASCADE;