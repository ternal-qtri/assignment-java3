CREATE DATABASE assignment_java3
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE assignment_java3;

CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    birthday DATE,
    gender BOOLEAN,
    mobile VARCHAR(20),
    email VARCHAR(100),
    avatar VARCHAR(255),   -- 👈 NÂNG CẤP
    role ENUM('ADMIN', 'REPORTER') NOT NULL
);

CREATE TABLE categories (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE news (
    id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    image VARCHAR(255),
    posted_date DATE,
    author_id VARCHAR(50),
    view_count INT DEFAULT 0,
    category_id VARCHAR(50),
    is_home BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_news_author
        FOREIGN KEY (author_id) REFERENCES users(id),

    CONSTRAINT fk_news_category
        FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE newsletters (
    email VARCHAR(100) PRIMARY KEY,
    enabled BOOLEAN DEFAULT TRUE
);

INSERT INTO users VALUES
('admin', '123', 'Quản trị viên', '1995-01-01', 1, '0900000000',
 'admin@abc.com', 'avatars/admin.png', 'ADMIN'),

('rep01', '123', 'Phóng viên A', '2000-05-10', 1, '0911111111',
 'rep01@abc.com', 'avatars/rep01.png', 'REPORTER');

INSERT INTO categories VALUES
('C01', 'Thời sự'),
('C02', 'Công nghệ'),
('C03', 'Thể thao');

INSERT INTO news VALUES
('N01', 'AI thay đổi thế giới',
 'Nội dung bài viết về AI...',
 'news/ai.jpg',
 CURRENT_DATE,
 'rep01',
 100,
 'C02',
 TRUE),

('N02', 'Việt Nam vô địch',
 'Nội dung thể thao...',
 'news/sport.jpg',
 CURRENT_DATE,
 'rep01',
 80,
 'C03',
 FALSE);

INSERT INTO newsletters VALUES
('user1@gmail.com', TRUE),
('user2@gmail.com', TRUE);
