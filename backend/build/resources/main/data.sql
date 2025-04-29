-- users 테이블 더미 데이터
INSERT INTO users (user_name, password, email, role_type, delete_yn, insert_date, update_date) VALUES
('Alice',   'pass1234', 'alice@example.com',   'USER',  'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bob',     'pass5678', 'bob@example.com',     'USER',  'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Carol',   'pass9012', 'carol@example.com',   'ADMIN', 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Dave',    'pass3456', 'dave@example.com',    'USER',  'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Eve',     'pass7890', 'eve@example.com',     'USER',  'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- bucket_list 테이블 더미 데이터
INSERT INTO bucket_list (user_id, contents, default_seq, bucket_done, insert_date, update_date) VALUES
(1, '스카이다이빙 하기',     1, 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '파리 여행 가기',       2, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '책 100권 읽기',        3, 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, '자전거로 전국 일주',    4, 'N', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, '마라톤 완주하기',      5, 'Y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- tag 테이블 더미 데이터
INSERT INTO tag (bucket_list_id, tag_name, insert_date, update_date) VALUES
(1, '도전',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, '여행',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, '자기계발',   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, '운동',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, '성취',       CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
