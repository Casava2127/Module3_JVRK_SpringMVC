package ra.crud.model;
// Đặt lớp `Department` trong package `ra.crud.model`, nơi chứa các entity (lớp ánh xạ với bảng trong cơ sở dữ liệu).

import lombok.*;
// Import các annotation từ thư viện Lombok để giảm thiểu việc viết mã (getter, setter, constructor, ...).

import javax.persistence.*;
// Import các annotation JPA để ánh xạ lớp này với bảng trong cơ sở dữ liệu.

import java.util.ArrayList;
import java.util.List;
// Import các lớp hỗ trợ danh sách, cần thiết cho quan hệ OneToMany.

@Entity
// Đánh dấu lớp `Department` là một Entity JPA, ánh xạ với một bảng trong cơ sở dữ liệu.

@Table(name = "Department")
// Đặt tên bảng trong cơ sở dữ liệu là "Department".

@NoArgsConstructor
// Lombok: Tự động tạo constructor không tham số.

@AllArgsConstructor
// Lombok: Tự động tạo constructor có tất cả tham số.

@Getter
@Setter
// Lombok: Tự động tạo getter và setter cho tất cả các thuộc tính.

public class Department {
    @Id
    // Đánh dấu thuộc tính `deptId` là khóa chính của bảng.
    @Column(name = "dept_id")
    // Gắn cột `dept_id` trong bảng với thuộc tính `deptId`.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Khóa chính sẽ tự động tăng (auto-increment) theo cơ chế của cơ sở dữ liệu.
    private int deptId;
    // Thuộc tính đại diện cho khóa chính của bảng `Department`.

    @Column(name = "dept_name", columnDefinition = "varchar(100)", unique = true, nullable = false)
    // Cột `dept_name` là kiểu VARCHAR (100 ký tự), giá trị phải duy nhất và không được null.
    private String deptName;
    // Tên của phòng ban.

    @Column(name = "dept_description", columnDefinition = "text")
    // Cột `dept_description` là kiểu TEXT, dùng để mô tả chi tiết về phòng ban.
    private String description;
    // Mô tả chi tiết phòng ban.

    @Column(name = "dept_status")
    // Cột `dept_status` lưu trạng thái phòng ban (true/false).
    private boolean status;
    // Trạng thái của phòng ban (hoạt động hoặc không hoạt động).

    @OneToMany(mappedBy = "department")
    // Định nghĩa mối quan hệ One-to-Many với entity `Employee`.
    // `mappedBy = "department"`: Khai báo khóa ngoại trong entity `Employee` liên kết với `Department`.
    private List<Employee> listEmloyees = new ArrayList<>();
    // Danh sách các nhân viên (Employee) thuộc phòng ban này.
    // Sử dụng `ArrayList` làm container mặc định để tránh lỗi null.
}
