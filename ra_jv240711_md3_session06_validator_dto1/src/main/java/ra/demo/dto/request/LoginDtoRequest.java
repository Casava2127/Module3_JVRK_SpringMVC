package ra.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDtoRequest {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String userName;
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String password;
}
