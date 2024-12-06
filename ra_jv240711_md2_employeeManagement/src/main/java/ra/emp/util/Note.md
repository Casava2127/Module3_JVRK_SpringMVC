Để xây dựng cơ sở dữ liệu MySQL và các procedure thực hiện các chức năng như bạn yêu cầu, chúng ta cần thực hiện các bước sau:

### 1. Cơ sở dữ liệu MySQL

#### a. Tạo cơ sở dữ liệu và bảng `Department`

```sql
CREATE DATABASE company;

USE company;

CREATE TABLE Department (
    Dept_id INT AUTO_INCREMENT PRIMARY KEY,
    Dept_name VARCHAR(100) NOT NULL UNIQUE,
    Dept_description TEXT NOT NULL,
    Dept_status BIT DEFAULT 1
);
```

#### b. Tạo bảng `Employee`

```sql
CREATE TABLE Employee (
    Emp_id CHAR(5) PRIMARY KEY,
    Emp_Name VARCHAR(100) NOT NULL,
    Emp_bod DATE NOT NULL,
    Emp_address TEXT NOT NULL,
    Emp_phone VARCHAR(15) NOT NULL UNIQUE,
    Emp_rate FLOAT NOT NULL CHECK (Emp_rate > 0),
    Dept_id INT,
    Emp_status INT NOT NULL CHECK (Emp_status IN (0, 1, 2)), 
    FOREIGN KEY (Dept_id) REFERENCES Department(Dept_id)
);
```

### 2. Các Procedure trong MySQL

#### a. Quản lý phòng ban

- **Thêm mới phòng ban:**

```sql
DELIMITER //

CREATE PROCEDURE AddDepartment(IN dept_name VARCHAR(100), IN dept_description TEXT)
BEGIN
    INSERT INTO Department (Dept_name, Dept_description, Dept_status)
    VALUES (dept_name, dept_description, 1); 
END //

DELIMITER ;
```

- **Cập nhật phòng ban:**

```sql
DELIMITER //

CREATE PROCEDURE UpdateDepartment(IN dept_id INT, IN dept_name VARCHAR(100), IN dept_description TEXT)
BEGIN
    UPDATE Department
    SET Dept_name = dept_name, Dept_description = dept_description
    WHERE Dept_id = dept_id;
END //

DELIMITER ;
```

- **Xóa phòng ban (chỉ xóa nếu không có nhân viên trong phòng ban):**

```sql
DELIMITER //

CREATE PROCEDURE DeleteDepartment(IN dept_id INT)
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Employee WHERE Dept_id = dept_id) THEN
        DELETE FROM Department WHERE Dept_id = dept_id;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot delete department with employees.';
    END IF;
END //

DELIMITER ;
```

- **Tìm kiếm phòng ban theo mã hoặc tên:**

```sql
DELIMITER //

CREATE PROCEDURE SearchDepartment(IN search_term VARCHAR(100))
BEGIN
    SELECT * FROM Department
    WHERE Dept_id = search_term OR Dept_name LIKE CONCAT('%', search_term, '%');
END //

DELIMITER ;
```

#### b. Quản lý nhân viên

- **Thêm mới nhân viên:**

```sql
DELIMITER //

CREATE PROCEDURE AddEmployee(IN emp_id CHAR(5), IN emp_name VARCHAR(100), IN emp_bod DATE, 
                              IN emp_address TEXT, IN emp_phone VARCHAR(15), IN emp_rate FLOAT, 
                              IN dept_id INT)
BEGIN
    INSERT INTO Employee (Emp_id, Emp_Name, Emp_bod, Emp_address, Emp_phone, Emp_rate, Dept_id, Emp_status)
    VALUES (emp_id, emp_name, emp_bod, emp_address, emp_phone, emp_rate, dept_id, 0);
END //

DELIMITER ;
```

- **Cập nhật nhân viên:**

```sql
DELIMITER //

CREATE PROCEDURE UpdateEmployee(IN emp_id CHAR(5), IN emp_name VARCHAR(100), IN emp_bod DATE, 
                                IN emp_address TEXT, IN emp_phone VARCHAR(15), IN emp_rate FLOAT, 
                                IN dept_id INT, IN emp_status INT)
BEGIN
    UPDATE Employee
    SET Emp_Name = emp_name, Emp_bod = emp_bod, Emp_address = emp_address, Emp_phone = emp_phone,
        Emp_rate = emp_rate, Dept_id = dept_id, Emp_status = emp_status
    WHERE Emp_id = emp_id;
END //

DELIMITER ;
```

- **Xóa nhân viên:**

```sql
DELIMITER //

CREATE PROCEDURE DeleteEmployee(IN emp_id CHAR(5))
BEGIN
    DELETE FROM Employee WHERE Emp_id = emp_id;
END //

DELIMITER ;
```

- **Tìm kiếm nhân viên theo mã, tên hoặc số điện thoại:**

```sql
DELIMITER //

CREATE PROCEDURE SearchEmployee(IN search_term VARCHAR(100))
BEGIN
    SELECT e.*, d.Dept_name
    FROM Employee e
    JOIN Department d ON e.Dept_id = d.Dept_id
    WHERE e.Emp_id = search_term OR e.Emp_Name LIKE CONCAT('%', search_term, '%') OR e.Emp_phone LIKE CONCAT('%', search_term, '%');
END //

DELIMITER ;
```

### 3. Ứng dụng Spring MVC

#### a. Cấu trúc Dự án SpringMVC:

- **Controller**: Xử lý các yêu cầu HTTP và điều hướng.
- **Service**: Chứa logic nghiệp vụ.
- **Repository**: Quản lý truy vấn cơ sở dữ liệu.
- **Model**: Chứa các đối tượng liên quan đến dữ liệu.

#### b. Tạo các Controller và Service:

Ví dụ về controller quản lý phòng ban:

```java
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "department/list";
    }

    @PostMapping("/department/add")
    public String addDepartment(@RequestParam String deptName, @RequestParam String deptDescription) {
        departmentService.addDepartment(deptName, deptDescription);
        return "redirect:/departments";
    }

    @GetMapping("/department/edit/{id}")
    public String editDepartment(@PathVariable int id, Model model) {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "department/edit";
    }

    @PostMapping("/department/update")
    public String updateDepartment(@RequestParam int deptId, @RequestParam String deptName, @RequestParam String deptDescription) {
        departmentService.updateDepartment(deptId, deptName, deptDescription);
        return "redirect:/departments";
    }

    @GetMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
```

#### c. Service Layer:

```java
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public void addDepartment(String deptName, String deptDescription) {
        departmentRepository.addDepartment(deptName, deptDescription);
    }

    public Department getDepartmentById(int deptId) {
        return departmentRepository.findById(deptId);
    }

    public void updateDepartment(int deptId, String deptName, String deptDescription) {
        departmentRepository.updateDepartment(deptId, deptName, deptDescription);
    }

    public void deleteDepartment(int deptId) {
        departmentRepository.deleteDepartment(deptId);
    }
}
```

#### d. Repository Layer (Dùng JDBC hoặc JPA để tương tác với cơ sở dữ liệu).

Ví dụ với JDBC:

```java
@Repository
public class DepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Department> findAll() {
        String sql = "SELECT * FROM Department";
        return jdbcTemplate.query(sql, new DepartmentRowMapper());
    }

    public void addDepartment(String deptName, String deptDescription) {
        String sql = "CALL AddDepartment(?, ?)";
        jdbcTemplate.update(sql, deptName, deptDescription);
    }

    public Department findById(int deptId) {
        String sql = "SELECT * FROM Department WHERE Dept_id = ?";
        return jdbcTemplate.queryForObject(sql, new DepartmentRowMapper(), deptId);
    }

    public void updateDepartment(int deptId, String deptName, String deptDescription) {
        String sql = "CALL UpdateDepartment(?, ?, ?)";
        jdbcTemplate.update(sql, deptId, deptName, deptDescription);
    }

    public void deleteDepartment(int deptId) {
        String sql = "CALL DeleteDepartment(?)";
        jdbcTemplate.update(sql, deptId);
    }
}
```

### 4. Giao diện người dùng:

- **Danh sách phòng ban**: Hiển thị danh sách các phòng ban.
- **Thêm, sửa, xóa phòng ban**: Các form cho phép thêm mới, sửa hoặc xóa phòng ban.
- **Danh sách nhân viên**: Hiển thị danh sách nhân viên cùng phòng ban tương ứng.
- **Tìm kiếm**: Cung cấp chức năng tìm kiếm nhân viên theo mã, tên hoặc số điện thoại.

Với cấu trúc trên, bạn có thể xây dựng ứng dụng Spring MVC để quản lý phòng ban và nhân viên trong công ty một cách dễ dàng.