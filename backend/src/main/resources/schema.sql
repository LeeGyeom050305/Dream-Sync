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