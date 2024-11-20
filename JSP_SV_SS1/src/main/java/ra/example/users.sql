-- Tạo bảng Users
Create database ExampleUser;
use ExampleUser;
CREATE TABLE Users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(100),
                       status BIT DEFAULT 1,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Thủ tục lấy tất cả người dùng
DELIMITER &&
CREATE PROCEDURE find_all_users()
BEGIN
SELECT * FROM Users;
END &&
DELIMITER ;

-- Thủ tục thêm mới người dùng
DELIMITER &&
CREATE PROCEDURE create_new_user(
    IN username_in VARCHAR(50),
    IN email_in VARCHAR(100),
    IN password_in VARCHAR(255),
    IN full_name_in VARCHAR(100),
    IN status_in BIT
)
BEGIN
INSERT INTO Users (username, email, password, full_name, status)
VALUES (username_in, email_in, password_in, full_name_in, status_in);
END &&
DELIMITER ;

-- Thủ tục tìm người dùng theo ID
DELIMITER &&
CREATE PROCEDURE find_user_by_id(IN user_id_in INT)
BEGIN
SELECT * FROM Users WHERE user_id = user_id_in;
END &&
DELIMITER ;

-- Thủ tục cập nhật thông tin người dùng
DELIMITER &&
CREATE PROCEDURE update_user(
    IN user_id_in INT,
    IN username_in VARCHAR(50),
    IN email_in VARCHAR(100),
    IN password_in VARCHAR(255),
    IN full_name_in VARCHAR(100),
    IN status_in BIT
)
BEGIN
UPDATE Users
SET username = username_in,
    email = email_in,
    password = password_in,
    full_name = full_name_in,
    status = status_in
WHERE user_id = user_id_in;
END &&
DELIMITER ;

-- Thủ tục xóa người dùng theo ID
DELIMITER &&
CREATE PROCEDURE delete_user(IN user_id_in INT)
BEGIN
DELETE FROM Users WHERE user_id = user_id_in;
END &&
DELIMITER ;

-- Ví dụ cách gọi các thủ tục đã tạo
-- CALL create_new_user('john_doe', 'john@example.com', 'hashed_password', 'John Doe', 1);
-- CALL find_all_users();
-- CALL find_user_by_id(1);
-- CALL update_user(1, 'john_doe', 'john_new@example.com', 'new_hashed_password', 'John Doe Updated', 1);
-- CALL delete_user(1);
-- Chèn dữ liệu mẫu vào bảng Users
INSERT INTO Users (username, email, password, full_name, status)
VALUES
    ('john_doe', 'john@example.com', 'hashed_password_1', 'John Doe', 1),
    ('jane_smith', 'jane@example.com', 'hashed_password_2', 'Jane Smith', 1),
    ('mike_brown', 'mike@example.com', 'hashed_password_3', 'Mike Brown', 0),
    ('susan_lee', 'susan@example.com', 'hashed_password_4', 'Susan Lee', 1),
    ('tom_jones', 'tom@example.com', 'hashed_password_5', 'Tom Jones', 1);

-- Kiểm tra kết quả chèn
SELECT * FROM Users;
