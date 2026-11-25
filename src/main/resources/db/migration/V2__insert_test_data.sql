-- 1. Вставляем тестовые заезды в таблицу races
-- Запоминаем их UUID, чтобы использовать в racers (race_id)
INSERT INTO races (id, prize, description, name, track, created_at, updated_at) VALUES
('a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', 150000.00, 'Финал сезона GT', 'Moscow GT cup', 'Moscow Raceway', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e', 30000.00, 'Легендарный заключительный этап дрифт серии', 'Moscow Drift series', 'Moscow Raceway', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 2. Вставляем тестовых гонщиков в таблицу racers
-- Обрати внимание: race_id ссылается на UUID заездов выше!
INSERT INTO racers (id, first_name, last_name, birth_date, nickname, racing_number, racing_class, race_id, created_at, updated_at) VALUES
('11111111-1111-1111-1111-111111111111', 'Иван', 'Иванов', '1990-05-15', 'Falcon', 7, 'GT', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('22222222-2222-2222-2222-222222222222', 'Петр', 'Петров', '1985-10-20', 'DriftKing', 13, 'DRIFT', 'b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('33333333-3333-3333-3333-333333333333', 'Олег', 'Сидоров', '2000-01-01', 'Speedy', 99, 'GT', 'a1b2c3d4-e5f6-7a8b-9c0d-1e2f3a4b5c6d', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);