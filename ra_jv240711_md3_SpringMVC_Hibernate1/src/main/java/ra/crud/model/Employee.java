package ra.crud.model;
// Đặt lớp `Employee` trong package `ra.crud.model`, nơi chứa các entity (lớp ánh xạ với bảng trong cơ sở dữ liệu).

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Các annotation của Lombok giúp tự động tạo constructor, getter và setter mà không cần phải viết thủ công.

import org.springframework.format.annotation.DateTimeFormat;
// Annotation này của Spring dùng để định dạng ngày tháng, giúp chuyển đổi giữa kiểu Date và chuỗi (String) trong các request và response.

import javax.persistence.*;
// Import các annotation JPA dùng để ánh xạ lớp Employee với bảng trong cơ sở dữ liệu.

import java.util.Date;
// Import lớp Date của Java, dùng để lưu trữ thông tin về ngày tháng.

@Entity
// Đánh dấu lớp `Employee` là một Entity JPA, ánh xạ với một bảng trong cơ sở dữ liệu.

@Table(name = "Employee")
// Đặt tên bảng trong cơ sở dữ liệu là "Employee".

@NoArgsConstructor
// Lombok: Tự động tạo constructor không tham số.

@AllArgsConstructor
// Lombok: Tự động tạo constructor có tất cả tham số.

@Getter
@Setter
// Lombok: Tự động tạo getter và setter cho tất cả các thuộc tính.

public class Employee {
    @Id
    @Column(name = "emp_id", columnDefinition = "char(5)")
    // Đánh dấu thuộc tính `empId` là khóa chính (primary key) của bảng.
    // Cột "emp_id" trong bảng có kiểu dữ liệu char với chiều dài 5 ký tự.
    private String empId;

    @Column(name = "emp_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    // Cột "emp_name" trong bảng có kiểu dữ liệu varchar với chiều dài 100 ký tự,
    // giá trị không được null và phải là duy nhất.
    private String empName;

    @Column(name = "emp_bod")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // Cột "emp_bod" lưu trữ ngày sinh của nhân viên. Annotation `DateTimeFormat` giúp định dạng ngày tháng theo mẫu "yyyy-MM-dd".
    private Date bod;

    @Column(name = "emp_salary", columnDefinition = "float check(emp_salary>0)", nullable = false)
    // Cột "emp_salary" lưu trữ mức lương nhân viên. Dữ liệu có kiểu float, yêu cầu giá trị lương phải lớn hơn 0 và không được null.
    private float salary;

    @Column(name = "emp_status")
    // Cột "emp_status" lưu trữ trạng thái của nhân viên (hoạt động hoặc không hoạt động).
    private boolean status;

    @ManyToOne
    // Đánh dấu mối quan hệ Many-to-One giữa `Employee` và `Department`. Một nhân viên chỉ thuộc một phòng ban, nhưng một phòng ban có thể có nhiều nhân viên.

    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    // Đánh dấu cột khóa ngoại trong bảng `Employee`. Cột "dept_id" trong bảng `Employee` liên kết với cột "dept_id" trong bảng `Department`.
    // Cột này ánh xạ mối quan hệ giữa nhân viên và phòng ban của họ.
    private Department department;
    // Thuộc tính `department` là đối tượng của lớp `Department`, đại diện cho phòng ban mà nhân viên này thuộc về.

}
