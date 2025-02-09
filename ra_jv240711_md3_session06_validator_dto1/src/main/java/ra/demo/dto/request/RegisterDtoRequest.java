package ra.demo.dto.request;
// Khai báo package chứa lớp `RegisterDtoRequest`.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
// Import các thư viện cần thiết, bao gồm Lombok, validation annotation, và các lớp hỗ trợ định dạng ngày.

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDtoRequest {
    // Sử dụng các annotation của Lombok để giảm thiểu mã nguồn (tự động tạo constructor, getter, setter).

    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String userName;
    // `@NotBlank`: Đảm bảo `userName` không được để trống hoặc chỉ chứa khoảng trắng.
    // Nếu không hợp lệ, thông báo lỗi "Tên đăng nhập không được để trống" sẽ hiển thị.

    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String password;
    // `@Size(min = 6)`: Đảm bảo `password` có độ dài tối thiểu là 6 ký tự.
    // Nếu không hợp lệ, thông báo lỗi "Mật khẩu tối thiểu 6 ký tự" sẽ hiển thị.

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;
    // `@NotBlank`: Đảm bảo `fullName` không được để trống.

    @Email(message = "Không đúng định dạng email")
    @NotBlank(message = "Email không được để trống")
    private String email;
    // `@Email`: Đảm bảo `email` tuân theo định dạng email hợp lệ.
    // `@NotBlank`: Đảm bảo `email` không được để trống.

    @Pattern(regexp = "(03|05|08|09)+([0|1|2|5|6|8])+(\\d{7})", message = "Sai định dạng số điện thoại VN")
    private String phone;
    // `@Pattern`: Ràng buộc định dạng của `phone` phải là số điện thoại Việt Nam hợp lệ:
    // Bắt đầu bằng `03`, `05`, `08`, hoặc `09`, theo sau là các số từ tập hợp `[0|1|2|5|6|8]`, và kết thúc bằng 7 chữ số.

    private boolean sex;
    // Đại diện cho giới tính, kiểu `boolean`.

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bod;
    // `@DateTimeFormat`: Định dạng ngày tháng năm sinh (`bod`) theo kiểu `yyyy-MM-dd`.

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
    // `@NotBlank`: Đảm bảo `address` không được để trống.
}
// ----> Nhận dữ liệu từ client khi người dùng đăng ký tài khoản mới.