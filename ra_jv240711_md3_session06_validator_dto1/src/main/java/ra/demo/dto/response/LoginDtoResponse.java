package ra.demo.dto.response;
// Khai báo package chứa lớp `LoginDtoResponse`.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Import các annotation từ Lombok để giảm thiểu việc viết mã nguồn thủ công.

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginDtoResponse {
    // Sử dụng Lombok để tự động sinh ra constructor, getter, và setter.

    private int userId;
    // Thuộc tính `userId`: Đại diện cho ID của người dùng.

    private String userName;
    // Thuộc tính `userName`: Đại diện cho tên đăng nhập của người dùng.

    private String fullName;
    // Thuộc tính `fullName`: Đại diện cho họ và tên đầy đủ của người dùng.
}
//Tóm lại: Khi đăng nhập thành công, backend sẽ trả về thông tin người dùng qua lớp này.
