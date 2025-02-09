package ra.demo.mapper;
// Khai báo package chứa lớp `LoginMapper`.

import org.springframework.stereotype.Component;
import ra.demo.dto.request.LoginDtoRequest;
import ra.demo.dto.response.LoginDtoResponse;
import ra.demo.model.User;
// Import các lớp cần thiết, bao gồm các DTO (`LoginDtoRequest`, `LoginDtoResponse`) và Entity (`User`).

@Component // bản chất chữ component ny là service + controller + repository = chung
public class LoginMapper implements GenericMapper<LoginDtoRequest, User, LoginDtoResponse> {
    // Đánh dấu lớp này là một Spring Component, giúp Spring tự động phát hiện và quản lý nó.
    // `GenericMapper` là một interface chung, định nghĩa các phương thức chuyển đổi giữa request, entity, và response.

    @Override
    public User mapperRequestToEntity(LoginDtoRequest request) {
        // Chuyển đổi từ `LoginDtoRequest` (dữ liệu từ phía người dùng) thành `User` (entity trong hệ thống).

        // Cách viết thủ công (đã comment):
        // User user = new User();
        // user.setUserName(request.getUserName());
        // user.setPassword(request.getPassword());
        // return user;

        // Cách viết gọn hơn bằng builder: -> thêm vào User.java
        return User.builder()
                .userName(request.getUserName()) // Gán giá trị `userName` từ request vào entity.
                .password(request.getPassword()) // Gán giá trị `password` từ request vào entity.
                .build(); // Trả về đối tượng `User`.
    }

    @Override
    public LoginDtoResponse mapperEntityToResponse(User entity) {
        // Chuyển đổi từ `User` (entity) thành `LoginDtoResponse` (dữ liệu phản hồi cho người dùng).

        if (entity != null) {
            // Nếu `entity` không phải null, tạo đối tượng `LoginDtoResponse` với các giá trị từ entity.
            return new LoginDtoResponse(
                    entity.getUserId(), // Lấy `userId` từ entity.
                    entity.getUserName(), // Lấy `userName` từ entity.
                    entity.getFullName()  // Lấy `fullName` từ entity.
            );
        }
        // Nếu entity là null, trả về null.
        return null;
    }
}
