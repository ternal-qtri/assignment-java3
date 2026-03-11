CREATE DATABASE assignment_java3;

GO USE assignment_java3;

GO
CREATE TABLE
    users (
        id INT IDENTITY PRIMARY KEY,
        username VARCHAR(50) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        fullname NVARCHAR (100) NOT NULL,
        email VARCHAR(100) NOT NULL UNIQUE,
        phone VARCHAR(20),
        birthday DATE,
        gender BIT, -- 1: Nam, 0: Nữ
        avatar VARCHAR(255),
        role BIT NOT NULL, -- 1: ADMIN, 0: REPORTER
        status BIT DEFAULT 1, -- 1: active, 0: disabled
        created_at DATETIME DEFAULT GETDATE ()
    );

CREATE TABLE
    categories (
        id VARCHAR(50) PRIMARY KEY,
        name NVARCHAR (100) NOT NULL
    );

CREATE TABLE
    news (
        id VARCHAR(50) PRIMARY KEY,
        title NVARCHAR (255) NOT NULL,
        content NVARCHAR (MAX) NOT NULL,
        image VARCHAR(255),
        posted_date DATE,
        author_id INT,
        view_count INT DEFAULT 0,
        category_id VARCHAR(50),
        is_home BIT DEFAULT 0,
        CONSTRAINT fk_news_author FOREIGN KEY (author_id) REFERENCES users (id),
        CONSTRAINT fk_news_category FOREIGN KEY (category_id) REFERENCES categories (id)
    );

CREATE TABLE
    newsletters (
        email VARCHAR(100) PRIMARY KEY,
        fullname NVARCHAR (100),
        enabled BIT DEFAULT 1
    );

INSERT INTO
    users (
        username,
        password,
        fullname,
        email,
        phone,
        birthday,
        gender,
        avatar,
        role
    )
VALUES
    (
        'admin',
        '123', -- demo, thực tế phải hash
        N'Quản trị viên',
        'admin@abc.com',
        '0900000000',
        '1995-01-01',
        1,
        'avatars/admin.png',
        1
    ),
    (
        'rep01',
        '123',
        N'Phóng viên A',
        'rep01@abc.com',
        '0911111111',
        '2000-05-10',
        1,
        'avatars/rep01.png',
        0
    );

INSERT INTO
    categories
VALUES
    ('C01', N'Thời sự'),
    ('C02', N'Công nghệ'),
    ('C03', N'Thể thao');

INSERT INTO
    news (
        id,
        title,
        content,
        image,
        posted_date,
        author_id,
        view_count,
        category_id,
        is_home
    )
VALUES
    (
        'N01',
        N'AI thay đổi thế giới',
        N'Nội dung bài viết về AI...',
        'news/ai.jpg',
        CAST(GETDATE () AS DATE),
        2,
        100,
        'C02',
        1
    ),
    (
        'N02',
        N'Việt Nam vô địch',
        N'Nội dung thể thao...',
        'news/sport.jpg',
        CAST(GETDATE () AS DATE),
        2,
        80,
        'C03',
        0
    );

INSERT INTO
    newsletters
VALUES
    ('user1@gmail.com', 'Khách 1', 1),
    ('user2@gmail.com', 'Khách 2', 1);