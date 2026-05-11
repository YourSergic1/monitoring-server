--liquibase formatted sql

--changeset titandea:05-01-add-fk-calendar-user
--comment: добавление связи между calendar и users
ALTER TABLE calendar
    ADD CONSTRAINT fk_calendar_user
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL;

--rollback ALTER TABLE calendar DROP CONSTRAINT fk_calendar_user;