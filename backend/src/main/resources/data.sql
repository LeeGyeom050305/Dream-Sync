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
INSERT INTO bucket_list (user_id, contents, default_seq, bucket_done, insert_date, update_date) VALUES
(1, '스카이다이빙 하기',     1, 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '파리 여행 가기',       2, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '책 100권 읽기',        3, 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, '자전거로 전국 일주',    4, 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, '마라톤 완주하기',      5, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tag 테이블 더미 데이터 (고유 태그만 입력)
INSERT INTO tag (tag_name) VALUES
('도전'),
('여행'),
('자기계발'),
('운동'),
('성취');

-- bucket_tag 테이블 더미 데이터 (버킷리스트 ID에 태그 연결)
INSERT INTO bucket_tag (bucket_list_id, tag_id) VALUES
(1, 1), -- 버킷리스트 1번에 '도전'
(2, 2), -- 버킷리스트 2번에 '여행'
(3, 3), -- 버킷리스트 3번에 '자기계발'
(4, 4), -- 버킷리스트 4번에 '운동'
(5, 5); -- 버킷리스트 5번에 '성취'
