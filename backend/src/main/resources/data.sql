-- users 테이블 더미 데이터
INSERT INTO users (user_name, password, email, role_type, delete_yn, insert_date, update_date) VALUES
-- 1234($2b$12$wuRgOZtw9cbDonYodyxDl.dqcW6OD8nlpdSkalEaEFKUK2xI67pdG)
('test',   '$2b$12$wuRgOZtw9cbDonYodyxDl.dqcW6OD8nlpdSkalEaEFKUK2xI67pdG', 'test@example.com',   'USER',  'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Alice',   '$2b$12$wuRgOZtw9cbDonYodyxDl.dqcW6OD8nlpdSkalEaEFKUK2xI67pdG', 'alice@example.com',   'USER',  'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bob',     '$2b$12$wuRgOZtw9cbDonYodyxDl.dqcW6OD8nlpdSkalEaEFKUK2xI67pdG', 'bob@example.com',     'USER',  'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Carol',   '$2b$12$wuRgOZtw9cbDonYodyxDl.dqcW6OD8nlpdSkalEaEFKUK2xI67pdG', 'carol@example.com',   'ADMIN', 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Dave',    '$2b$12$wuRgOZtw9cbDonYodyxDl.dqcW6OD8nlpdSkalEaEFKUK2xI67pdG', 'dave@example.com',    'USER',  'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Eve',     '$2b$12$wuRgOZtw9cbDonYodyxDl.dqcW6OD8nlpdSkalEaEFKUK2xI67pdG', 'eve@example.com',     'USER',  'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- bucket_list 테이블 더미 데이터
INSERT INTO bucket_list (user_id, contents, default_seq, bucket_done, visible, insert_date, update_date) VALUES
(1, '스카이다이빙 하기',     1, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '파리 여행 가기',       2, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '책 100권 읽기',        3, false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, '자전거로 전국 일주',    4, false, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, '마라톤 완주하기',      5, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tag 테이블 더미 데이터
INSERT INTO tag (bucket_list_id, tag_name, insert_date, update_date) VALUES
(1, '도전',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '여행',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '자기계발',   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, '운동',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, '성취',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO bucket_list_numbers (bucket_list_id, user_id) VALUES
(1, 101),
(1, 102),
(1, 103),
(2, 201),
(2, 202),
(3, 301),
(3, 302),
(4, 401),
(5, 501),
(5, 502);