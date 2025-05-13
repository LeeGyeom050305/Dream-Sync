DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role_type VARCHAR(50),
    delete_yn VARCHAR(1),
    insert_date TIMESTAMP,
    update_date TIMESTAMP
);

DROP TABLE IF EXISTS bucket_list CASCADE;
CREATE TABLE bucket_list (
    bucket_list_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    contents VARCHAR(200),
    default_seq INT,
    bucket_done BOOLEAN NOT NULL DEFAULT FALSE,
    visible BOOLEAN NOT NULL DEFAULT FALSE,
    insert_date TIMESTAMP,
    update_date TIMESTAMP,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
);

DROP TABLE IF EXISTS tag CASCADE;
CREATE TABLE tag (
    tag_id INT AUTO_INCREMENT PRIMARY KEY,
    tag_name VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS bucket_tag CASCADE;
CREATE TABLE bucket_tag (
    bucket_tag_id INT AUTO_INCREMENT PRIMARY KEY,
    bucket_list_id INT NOT NULL,
    tag_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bucket_list_id) REFERENCES bucket_list(bucket_list_id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(tag_id) ON DELETE CASCADE,
    UNIQUE (bucket_list_id, tag_id) -- 중복 방지
);

DROP TABLE IF EXISTS bucket_list_numbers CASCADE;
CREATE TABLE bucket_list_numbers (
    bucket_list_id INT NOT NULL,
    user_id INT,
    CONSTRAINT fk_bucket_list_numbers
        FOREIGN KEY (bucket_list_id)
        REFERENCES bucket_list(bucket_list_id)
        ON DELETE CASCADE
);

DROP TABLE IF EXISTS friend_requests CASCADE;
CREATE TABLE friend_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'pending' CHECK (status IN ('pending', 'accepted', 'rejected')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_friend_sender FOREIGN KEY (sender_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_friend_receiver FOREIGN KEY (receiver_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT uc_sender_receiver UNIQUE (sender_id, receiver_id)
);

DROP TABLE IF EXISTS user_blocks CASCADE;
CREATE TABLE user_blocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    blocker_id INT NOT NULL,
    blocked_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_blocker FOREIGN KEY (blocker_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_blocked FOREIGN KEY (blocked_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT uc_blocker_blocked UNIQUE (blocker_id, blocked_id)
);
