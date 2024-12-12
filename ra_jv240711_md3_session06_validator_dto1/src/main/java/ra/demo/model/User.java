package ra.demo.model;
// Khai báo package chứa lớp này là `ra.demo.model`.

import lombok.*;
// Import tất cả các annotation của Lombok để tự động sinh mã (constructor, getter, setter, builder, ...).

import org.springframework.format.annotation.DateTimeFormat;
// Import annotation `DateTimeFormat` để định dạng ngày tháng trong các trường kiểu `LocalDate`.

import javax.persistence.*;
// Import các annotation của JPA để ánh xạ lớp này thành bảng trong cơ sở dữ liệu.

import java.time.LocalDate;
// Import lớp `LocalDate` để sử dụng kiểu ngày tháng.

@Entity
// Đánh dấu lớp này là một thực thể (Entity) được quản lý bởi JPA.

@Table(name = "App_User")
// Ánh xạ lớp này với bảng `App_User` trong cơ sở dữ liệu.

@NoArgsConstructor
// Tự động tạo constructor không tham số.

@AllArgsConstructor
// Tự động tạo constructor có đầy đủ tham số cho tất cả các thuộc tính.

@Getter
// Tự động tạo getter cho tất cả các thuộc tính.

@Setter
// Tự động tạo setter cho tất cả các thuộc tính.

@Builder
// Tự động tạo phương thức Builder để xây dựng đối tượng dễ dàng.

public class User {
    @Id
    // Đánh dấu `userId` là khóa chính của bảng.

    @Column(name = "user_id")
    // Ánh xạ thuộc tính `userId` với cột `user_id` trong bảng.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Khai báo chiến lược sinh giá trị tự động (Identity) cho khóa chính.

    private int userId;
    // Thuộc tính `userId` là số nguyên, lưu trữ mã người dùng.

    @Column(name = "user_name", columnDefinition = "varchar(100)", unique = true, nullable = false)
    // Ánh xạ với cột `user_name`, kiểu varchar(100), giá trị là duy nhất và không được null.

    private String userName;
    // Thuộc tính `userName` lưu trữ tên đăng nhập.

    @Column(name = "user_passwod", columnDefinition = "varchar(50)", nullable = false)
    // Ánh xạ với cột `user_passwod`, kiểu varchar(50), không được null.

    private String password;
    // Thuộc tính `password` lưu trữ mật khẩu người dùng.

    @Column(name = "user_fullname", columnDefinition = "varchar(100)", nullable = false)
    // Ánh xạ với cột `user_fullname`, kiểu varchar(100), không được null.

    private String fullName;
    // Thuộc tính `fullName` lưu trữ họ và tên đầy đủ của người dùng.

    @Column(name = "user_email", columnDefinition = "varchar(100)", unique = true, nullable = false)
    // Ánh xạ với cột `user_email`, kiểu varchar(100), giá trị là duy nhất và không được null.

    private String email;
    // Thuộc tính `email` lưu trữ địa chỉ email của người dùng.

    @Column(name = "user_phone", columnDefinition = "char(10)", unique = true, nullable = false)
    // Ánh xạ với cột `user_phone`, kiểu char(10), giá trị là duy nhất và không được null.

    private String phone;
    // Thuộc tính `phone` lưu trữ số điện thoại của người dùng.

    @Column(name = "user_sex")
    // Ánh xạ với cột `user_sex`.

    private boolean sex;
    // Thuộc tính `sex` lưu trữ giới tính của người dùng (true: nam, false: nữ).

    @Column(name = "user_bod")
    // Ánh xạ với cột `user_bod`.

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // Định dạng ngày tháng theo mẫu `yyyy-MM-dd`.

    private LocalDate bod;
    // Thuộc tính `bod` lưu trữ ngày sinh của người dùng.

    @Column(name = "user_address", nullable = false)
    // Ánh xạ với cột `user_address`, không được null.

    private String address;
    // Thuộc tính `address` lưu trữ địa chỉ của người dùng.

    @Column(name = "user_created")
    // Ánh xạ với cột `user_created`.

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // Định dạng ngày tháng theo mẫu `yyyy-MM-dd`.

    private LocalDate created = LocalDate.now();
    // Thuộc tính `created` lưu trữ ngày tạo tài khoản, mặc định là ngày hiện tại.

    @Column(name = "user_status", columnDefinition = "bit default(1)")
    // Ánh xạ với cột `user_status`, kiểu bit, giá trị mặc định là `1`.

    private boolean status = true;
    // Thuộc tính `status` lưu trữ trạng thái tài khoản (true: hoạt động, false: không hoạt động).

}
