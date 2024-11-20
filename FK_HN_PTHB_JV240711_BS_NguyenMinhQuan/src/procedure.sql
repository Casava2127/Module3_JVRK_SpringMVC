

SHOW PROCEDURE STATUS WHERE Db = 'quanlybanhang';
-- Procedure to get the list of categories
DELIMITER //
CREATE PROCEDURE ProcGetCategories()
BEGIN
SELECT * FROM Categories;
END //
DELIMITER ;

-- Procedure to create a new category
DELIMITER //
CREATE PROCEDURE ProcCreateCategory(IN categoryName VARCHAR(50))
BEGIN
INSERT INTO Categories (category_name) VALUES (categoryName);
END //
DELIMITER ;

-- Procedure to update a category
DELIMITER //
CREATE PROCEDURE ProcUpdateCategory(IN categoryId INT, IN categoryName VARCHAR(50), IN categoryStatus BIT)
BEGIN
UPDATE Categories
SET category_name = categoryName, category_status = categoryStatus
WHERE category_id = categoryId;
END //
DELIMITER ;

-- Procedure to delete a category
DELIMITER //
CREATE PROCEDURE ProcDeleteCategory(IN categoryId INT)
BEGIN
DELETE FROM Categories WHERE category_id = categoryId;
END //
DELIMITER ;

-- Procedure to count products by category
DELIMITER //
CREATE PROCEDURE ProcCountProductsByCategory(IN categoryId INT)
BEGIN
    SELECT COUNT(*) AS product_count
    FROM Products
    WHERE category_id = categoryId;
END //
DELIMITER ;


-- Procedure to get the list of products
DELIMITER //
CREATE PROCEDURE ProcGetProducts()
BEGIN
SELECT * FROM Products;
END //
DELIMITER ;

-- Procedure to create a new product
DELIMITER //
CREATE PROCEDURE ProcCreateProduct(IN productName VARCHAR(20), IN stock INT, IN costPrice DOUBLE, IN sellingPrice DOUBLE, IN categoryId INT)
BEGIN
INSERT INTO Products (product_name, stock, cost_price, selling_price, category_id)
VALUES (productName, stock, costPrice, sellingPrice, categoryId);
END //
DELIMITER ;

-- Procedure to update a product
DELIMITER //
CREATE PROCEDURE ProcUpdateProduct(IN productId INT, IN productName VARCHAR(20), IN stock INT, IN costPrice DOUBLE, IN sellingPrice DOUBLE)
BEGIN
UPDATE Products
SET product_name = productName, stock = stock, cost_price = costPrice, selling_price = sellingPrice
WHERE product_id = productId;
END //
DELIMITER ;

-- Procedure to delete a product
DELIMITER //
CREATE PROCEDURE ProcDeleteProduct(IN productId INT)
BEGIN
DELETE FROM Products WHERE product_id = productId;
END //
DELIMITER ;

-- Procedure to display products by creation date (descending)
DELIMITER //
CREATE PROCEDURE ProcGetProductsByDate()
BEGIN
SELECT * FROM Products ORDER BY created_at DESC;
END //
DELIMITER ;

-- Procedure to search products by price range
DELIMITER //
CREATE PROCEDURE ProcSearchProductsByPrice(IN minPrice DOUBLE, IN maxPrice DOUBLE)
BEGIN
SELECT * FROM Products WHERE selling_price BETWEEN minPrice AND maxPrice;
END //
DELIMITER ;

-- Procedure to get top 3 profitable products
DELIMITER //
CREATE PROCEDURE ProcTop3ProfitableProducts()
BEGIN
SELECT product_name, (selling_price - cost_price) AS profit
FROM Products
ORDER BY profit DESC
    LIMIT 3;
END //
DELIMITER ;

SHOW PROCEDURE STATUS WHERE Name = 'ProcCountProductsByCategory';
