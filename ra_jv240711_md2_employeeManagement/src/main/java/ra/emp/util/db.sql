create database SpringMVC_DB;
       use SpringMVC_DB;


CREATE TABLE Department (
                            Dept_id INT AUTO_INCREMENT PRIMARY KEY,
                            Dept_name VARCHAR(100) NOT NULL UNIQUE,
                            Dept_description TEXT NOT NULL,
                            Dept_status BIT DEFAULT 1
);
CREATE TABLE Employee (
                          Emp_id CHAR(5) PRIMARY KEY,
                          Emp_Name VARCHAR(100) NOT NULL,
                          Emp_bod DATE NOT NULL,
                          Emp_address TEXT NOT NULL,
                          Emp_phone VARCHAR(15) ,
                          Emp_rate FLOAT NOT NULL CHECK (Emp_rate > 0),
                          Dept_id INT,
                          Emp_status INT NOT NULL CHECK (Emp_status IN (0, 1, 2)),
                          FOREIGN KEY (Dept_id) REFERENCES Department(Dept_id)
);
# insert table
INSERT INTO Department (Dept_name, Dept_description, Dept_status)
VALUES
    ('IT', 'Phòng Công nghệ thông tin, chuyên hỗ trợ các vấn đề kỹ thuật và phát triển phần mềm.', 1),
    ('HR', 'Phòng Nhân sự, chịu trách nhiệm quản lý tuyển dụng, đào tạo và các vấn đề về nhân sự.', 1),
    ('Marketing', 'Phòng Marketing, chuyên về các chiến lược quảng bá sản phẩm và thương hiệu.', 1),
    ('Finance', 'Phòng Tài chính, quản lý các vấn đề tài chính và ngân sách.', 1),
    ('Operations', 'Phòng Vận hành, tập trung vào quản lý quy trình kinh doanh.', 1),
    ('Sales', 'Phòng Kinh doanh, chịu trách nhiệm bán hàng và phát triển thị trường.', 1),
    ('Support', 'Phòng Hỗ trợ khách hàng, chuyên xử lý các yêu cầu hỗ trợ.', 1),
    ('Legal', 'Phòng Pháp chế, chịu trách nhiệm về vấn đề pháp lý của công ty.', 1),
    ('Admin', 'Phòng Hành chính, hỗ trợ về mặt hành chính và cơ sở vật chất.', 1),
    ('Research', 'Phòng Nghiên cứu, tập trung vào phát triển và nghiên cứu sản phẩm mới.', 1);
INSERT INTO Employee (Emp_id, Emp_Name, Emp_bod, Emp_address, Emp_phone, Emp_rate, Dept_id, Emp_status)
VALUES
    ('E001', 'Alice Brown', '1990-01-01', '123 Main St', '1111', 1.5, 1, 1),
    ('E002', 'Bob Smith', '1991-02-02', '234 Elm St', '2222', 1.6, 2, 1),
    ('E003', 'Charlie Johnson', '1992-03-03', '345 Oak St', '3333', 1.7, 3, 1),
    ('E004', 'Daisy Miller', '1993-04-04', '456 Birch St', '4444', 1.8, 4, 1),
    ('E005', 'Ethan Williams', '1994-05-05', '567 Willow St', '5555', 1.9, 5, 1),
    ('E006', 'Michael Davis', '1995-06-06', '678 Pine St', '6666', 1.7, 6, 0),
    ('E007', 'Jessica Thompson', '1996-07-07', '789 Cedar St', '7777', 1.8, 7, 0),
    ('E008', 'Emily Johnson', '1997-08-08', '890 Maple St', '8888', 1.6, 8, 0),
    ('E009', 'Franklin Harris', '1998-09-09', '901 Spruce St', '9999', 1.7, 9, 2),
    ('E010', 'Grace Lee', '1999-10-10', '101 Sycamore St', '1010', 1.5, 10, 2);

DELIMITER &&

CREATE PROCEDURE FindAllDepartment()
BEGIN
    SELECT * FROM Department;
#     WHERE Emp_status = 0;
END &&
DELIMITER ;

DELIMITER &&

CREATE PROCEDURE UpdateDepartment(IN deptid INT, IN deptname VARCHAR(100), IN deptdescription TEXT)
BEGIN
    UPDATE Department
    SET Dept_name = deptname, Dept_description = deptdescription
    WHERE Department.Dept_id = deptid;
END &&

DELIMITER ;

DELIMITER &&

CREATE PROCEDURE DeleteDepartment(IN deptid INT)
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Employee WHERE Employee.Dept_id = deptid) THEN
        DELETE FROM Department WHERE Department.Dept_id = deptid;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot delete department with employees.';
    END IF;
END &&

DELIMITER ;

DELIMITER &&

CREATE PROCEDURE SearchDepartment(IN search_term VARCHAR(100))
BEGIN
    SELECT * FROM Department
    WHERE Dept_id = search_term OR Dept_name LIKE CONCAT('%', search_term, '%');
END &&

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE AddDepartment(
    IN dept_name VARCHAR(100),
    IN dept_description TEXT,
    IN dept_status BIT
)
BEGIN
    IF EXISTS (SELECT 1 FROM Department WHERE Dept_name = dept_name) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Department name already exists.';
    ELSE
        INSERT INTO Department (Dept_name, Dept_description, Dept_status)
        VALUES (dept_name, dept_description, dept_status);
    END IF;
END $$

DELIMITER ;






# procedure find_all_epm
DELIMITER &&

CREATE PROCEDURE FindAllEmployees()
BEGIN
    SELECT * FROM Employee;
#     WHERE Emp_status = 0;
END &&
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE AddEmployee(
    IN emp_id CHAR(5),
    IN emp_name VARCHAR(100),
    IN emp_bod DATE,
    IN emp_address TEXT,
    IN emp_phone VARCHAR(15),
    IN emp_rate FLOAT,
    IN dept_id INT,
    IN emp_status INT
)
BEGIN
    INSERT INTO Employee (
        Emp_id, Emp_Name, Emp_bod, Emp_address, Emp_phone, Emp_rate, Dept_id, Emp_status
    )
    VALUES (
               emp_id, emp_name, emp_bod, emp_address, emp_phone, emp_rate, dept_id, emp_status
           );
END $$

DELIMITER ;


DELIMITER &&
CREATE PROCEDURE UpdateEmployee(
    IN empid CHAR(5),
    IN empname VARCHAR(100),
    IN empbod DATE,
    IN empaddress TEXT,
    IN empphone VARCHAR(15),
    IN emprate FLOAT,
    IN deptid INT,
    IN empstatus INT
)
BEGIN
    -- Cập nhật chỉ một bản ghi duy nhất với emp_id
    UPDATE Employee
    SET
        Emp_Name = empname,
        Emp_bod = empbod,
        Emp_address = empaddress,
        Emp_phone = empphone,
        Emp_rate = emprate,
        Dept_id = deptid,
        Emp_status = empstatus
    WHERE Emp_id = empid;  -- Cập nhật theo emp_id duy nhất
END &&;
DELIMITER &&;

# select * from employee where employee.Emp_id="E006";


DELIMITER &&

CREATE PROCEDURE DeleteEmployee(IN empid CHAR(5))
BEGIN
    DELETE FROM Employee WHERE Employee.Emp_id = empid;
END &&

DELIMITER ;

DELIMITER &&

CREATE PROCEDURE SearchEmployee(IN search_term VARCHAR(100))
BEGIN
    SELECT e.*, d.Dept_name
    FROM Employee e
             JOIN Department d ON e.Dept_id = d.Dept_id
    WHERE e.Emp_id = search_term OR e.Emp_Name LIKE CONCAT('%', search_term, '%') OR e.Emp_phone LIKE CONCAT('%', search_term, '%');
END &&

DELIMITER ;

# FindEmployeeById
DELIMITER $$

CREATE PROCEDURE FindEmployeeById(IN emp_id CHAR(5))
BEGIN
    SELECT * FROM Employee WHERE Employee.Emp_id = emp_id;
end $$


DELIMITER ;

# update

