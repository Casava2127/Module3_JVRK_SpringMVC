package ra.demo.dto.request;

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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterDtoRequest {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String userName;
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String password;
    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;
    @Email(message = "Không đúng định dạng email")
    @NotBlank(message = "Email không được để trống")
    private String email;
    @Pattern(regexp = "(03|05|08|09)+([0|1|2|5|6|8])+(\\d{7})", message = "Sai định dạng số điện thoại VN")
    private String phone;
    private boolean sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bod;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;
}
