-- Tạo bảng Categories
create  database quanlybanhang;
use quanlybanhang;
CREATE TABLE Categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL UNIQUE,
    category_status BIT DEFAULT 1
);

-- Tạo bảng Products
CREATE TABLE Products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(20) NOT NULL UNIQUE,
    stock INT NOT NULL,
    cost_price DOUBLE NOT NULL CHECK (cost_price > 0),
    selling_price DOUBLE NOT NULL CHECK (selling_price > 0),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES Categories(category_id)
);
INSERT INTO Categories (category_name, category_status) VALUES
                                                            ('Đồ uống', 1),
                                                            ('Thức ăn nhanh', 1),
                                                            ('Trái cây', 1),
                                                            ('Bánh kẹo', 1),
                                                            ('Sản phẩm từ sữa', 1),
                                                            ('Thực phẩm chức năng', 1),
                                                            ('Gia vị', 1),
                                                            ('Nước giải khát', 1),
                                                            ('Thực phẩm đông lạnh', 1),
                                                            ('Thực phẩm hữu cơ', 1);
INSERT INTO Products (product_name, stock, cost_price, selling_price, created_at, category_id) VALUES
                                                                                                   ('Nước ngọt', 100, 10.0, 15.0, NOW(), 1),
                                                                                                   ('Hamburger', 50, 15.0, 25.0, NOW(), 2),
                                                                                                   ('Bánh quy', 200, 5.0, 10.0, NOW(), 4),
                                                                                                   ('Nước cam', 80, 20.0, 30.0, NOW(), 1),
                                                                                                   ('Sữa tươi', 150, 12.0, 18.0, NOW(), 5),
                                                                                                   ('Trái cây hỗn hợp', 60, 25.0, 35.0, NOW(), 3),
                                                                                                   ('Kẹo dẻo', 120, 7.0, 12.0, NOW(), 4),
                                                                                                   ('Bánh mì', 200, 4.0, 6.0, NOW(), 2),
                                                                                                   ('Nước dừa', 90, 18.0, 25.0, NOW(), 1),
                                                                                                   ('Sữa chua', 75, 8.0, 12.0, NOW(), 5);
select * from Categories;
select * from products;