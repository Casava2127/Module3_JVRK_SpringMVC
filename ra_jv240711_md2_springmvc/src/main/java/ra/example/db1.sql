-- Tạo cơ sở dữ liệu
CREATE DATABASE bookmanager_db1;
USE bookmanager_db1;

-- Tạo bảng categories
CREATE TABLE categories (
                            category_id INT AUTO_INCREMENT PRIMARY KEY,
                            category_name VARCHAR(100) NOT NULL UNIQUE
);

-- Tạo bảng book
CREATE TABLE book (
                      book_id INT AUTO_INCREMENT PRIMARY KEY,
                      book_name VARCHAR(100) NOT NULL UNIQUE,
                      book_price FLOAT,
                      book_status BOOLEAN,
                      category_id INT,
                      FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
DELIMITER &&
CREATE PROCEDURE find_all_categories()
BEGIN
SELECT * FROM categories;
END &&
DELIMITER ;

DELIMITER &&
CREATE PROCEDURE create_category(IN name VARCHAR(100))
BEGIN
INSERT INTO categories (category_name) VALUES (name);
END &&
DELIMITER ;

    DELIMITER &&
CREATE PROCEDURE update_category(IN id INT, IN name VARCHAR(100))
BEGIN
UPDATE categories SET category_name = name WHERE category_id = id;
END &&
DELIMITER ;

    DELIMITER &&
CREATE PROCEDURE delete_category(IN id INT)
BEGIN
DELETE FROM categories WHERE category_id = id;
END &&
DELIMITER ;


    DELIMITER &&
CREATE PROCEDURE find_category_by_id(IN id INT)
BEGIN
SELECT * FROM categories WHERE category_id = id;
END &&
DELIMITER ;


    DELIMITER &&
CREATE PROCEDURE find_all_books()
BEGIN
SELECT * FROM book;
END &&
DELIMITER ;


    DELIMITER &&
CREATE PROCEDURE create_book(
    IN name VARCHAR(100),
    IN price FLOAT,
    IN status BOOLEAN,
    IN category INT
)
BEGIN
INSERT INTO book (book_name, book_price, book_status, category_id)
VALUES (name, price, status, category);
END &&
DELIMITER ;


    DELIMITER &&
CREATE PROCEDURE update_book(
    IN id INT,
    IN name VARCHAR(100),
    IN price FLOAT,
    IN status BOOLEAN,
    IN category INT
)
BEGIN
UPDATE book
SET book_name = name, book_price = price, book_status = status, category_id = category
WHERE book_id = id;
END &&
DELIMITER ;


    DELIMITER &&
CREATE PROCEDURE delete_book(IN id INT)
BEGIN
DELETE FROM book WHERE book_id = id;
END &&
DELIMITER ;


    DELIMITER &&
CREATE PROCEDURE find_book_by_id(IN id INT)
BEGIN
SELECT * FROM book WHERE book_id = id;
END &&
DELIMITER ;


    -- Chèn dữ liệu mẫu vào bảng categories
INSERT INTO categories (category_name) VALUES ('Fiction');
INSERT INTO categories (category_name) VALUES ('Non-Fiction');

-- Chèn dữ liệu mẫu vào bảng book
INSERT INTO book (book_name, book_price, book_status, category_id) VALUES
                                                                       ('The Catcher in the Rye', 9.99, 1, 1),
                                                                       ('To Kill a Mockingbird', 10.99, 1, 1),
                                                                       ('Sapiens', 15.50, 1, 2);