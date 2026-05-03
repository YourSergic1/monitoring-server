--liquibase formatted sql

--changeset titandea:01-01-create-cpu-metrics-tables
--comment: создание таблицы метрик ЦПУ
CREATE TABLE cpu_metrics
(
    id                 UUID PRIMARY KEY,
    system_id          UUID NOT NULL REFERENCES system_metrics (id) ON DELETE CASCADE,
    usage_percent      DOUBLE PRECISION,
    user_percent       DOUBLE PRECISION,
    system_percent     DOUBLE PRECISION,
    iowait_percent     DOUBLE PRECISION,
    load_average_1     DOUBLE PRECISION,
    load_average_5     DOUBLE PRECISION,
    load_average_15    DOUBLE PRECISION,
    temperature        DOUBLE PRECISION,
    physical_cores     INT,
    logical_processors INT
);
CREATE INDEX idx_cpu_system ON cpu_metrics (system_id);
--rollback DROP TABLE cpu_metrics CASCADE;