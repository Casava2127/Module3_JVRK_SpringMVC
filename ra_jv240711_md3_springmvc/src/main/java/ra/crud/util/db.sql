create database bookmanager_db;
use bookmanager_db;
--     create book table
create table book(
                     book_id INT AUTO_INCREMENT PRIMARY KEY,
                     book_name VARCHAR(100) NOT NULL unique,
                     book_price float,
                     book_status bit
);
-- # procedure
DELIMITER &&
CREATE PROCEDURE find_all_book()
BEGIN
SELECT * FROM book;
END &&
  DELIMITER &&;

  -- # procedure create
      DELIMITER &&
CREATE PROCEDURE create_book(IN name VARCHAR(100), IN price FLOAT, IN status BOOLEAN)
BEGIN
INSERT INTO book (book_name, book_price, book_status) VALUES (name, price, status);
END &&
DELIMITER && ;
-- # procedure update
DELIMITER &&
CREATE PROCEDURE update_book(IN id INT, IN name VARCHAR(100), IN price FLOAT, IN status BOOLEAN)
BEGIN
UPDATE book SET book_name = name, book_price = price, book_status = status WHERE book_id = id;
END &&
DELIMITER ;

-- # procedure delete
DELIMITER &&
CREATE PROCEDURE delete_book(IN id INT)
BEGIN
DELETE FROM book WHERE book_id = id;
END &&
DELIMITER &&;

# producere find_book_by_id
DELIMITER &&
CREATE PROCEDURE find_book_by_id(IN id INT)
BEGIN
SELECT * FROM book WHERE book_id = id;
END &&
DELIMITER &&;
# insert data into table
INSERT INTO book (book_name, book_price, book_status) VALUES ('The Catcher in the Rye', 9.99, 1);
INSERT INTO book (book_name, book_price, book_status) VALUES ('To Kill a Mockingbird', 10.99, 1);

# use bookmanager_db;
# select  * from book;