package ra.demo.dto.request;
// Khai báo package chứa lớp `LoginDtoRequest`.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
// Import các annotation và thư viện cần thiết.

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDtoRequest {
    // Sử dụng các annotation của Lombok để giảm thiểu mã nguồn (tự động tạo constructor, getter, setter).

    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String userName;
    // `@NotBlank`: Đảm bảo trường `userName` không được để trống hoặc chỉ chứa khoảng trắng.
    // Nếu vi phạm, thông báo lỗi "Tên đăng nhập không được để trống" sẽ hiển thị.

    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String password;
    // `@Size(min = 6)`: Đảm bảo trường `password` có độ dài tối thiểu là 6 ký tự.
    // Nếu vi phạm, thông báo lỗi "Mật khẩu tối thiểu 6 ký tự" sẽ hiển thị.
}
//Tóm lại: Lớp này giúp kiểm tra đầu vào của form đăng nhập, tránh trường hợp gửi thông tin sai lên server.