--liquibase formatted sql

--changeset titandea:04-01-create-network-metrics-tables
--comment: создание таблицы метрик диска
CREATE TABLE network_metrics(
    id             UUID PRIMARY KEY,
    system_id      UUID         NOT NULL REFERENCES system_metrics (id) ON DELETE CASCADE,
    interface_name VARCHAR(255),
    bytes_sent     BIGINT,
    bytes_recv     BIGINT,
    packets_sent   BIGINT,
    packets_recv   BIGINT,
    in_errors      BIGINT,
    out_errors     BIGINT,
    speed          BIGINT
);
CREATE INDEX idx_network_system ON network_metrics (system_id);
--rollback DROP TABLE network_metrics CASCADE;