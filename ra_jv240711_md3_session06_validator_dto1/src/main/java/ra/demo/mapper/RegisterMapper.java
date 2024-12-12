package ra.demo.mapper;

import org.springframework.stereotype.Component;
import ra.demo.dto.request.RegisterDtoRequest;
import ra.demo.model.User;

import java.time.LocalDate;

@Component
public class RegisterMapper<P> implements GenericMapper<RegisterDtoRequest, User, P> {
    // Đánh dấu lớp là một Spring component, giúp Spring tự động phát hiện và quản lý lớp này.
    // Lớp này thực hiện chuyển đổi giữa `RegisterDtoRequest` (request từ người dùng) và `User` (entity).

    @Override
    public User mapperRequestToEntity(RegisterDtoRequest request) {
        // Phương thức này chuyển đổi từ DTO `RegisterDtoRequest` sang entity `User`.

        User user = new User();
        user.setUserName(request.getUserName());  // Gán giá trị `userName` từ request vào `User`.
        user.setPassword(request.getPassword());  // Gán giá trị `password` từ request vào `User`.
        user.setFullName(request.getFullName());  // Gán giá trị `fullName` từ request vào `User`.
        user.setEmail(request.getEmail());       // Gán giá trị `email` từ request vào `User`.
        user.setPhone(request.getPhone());       // Gán giá trị `phone` từ request vào `User`.
        user.setSex(request.isSex());            // Gán giá trị `sex` từ request vào `User`.
        user.setAddress(request.getAddress());   // Gán giá trị `address` từ request vào `User`.
        user.setBod(request.getBod());           // Gán giá trị `bod` (ngày sinh) từ request vào `User`.

        return user;  // Trả về đối tượng `User` đã được điền đầy đủ thông tin.
    }

    @Override
    public P mapperEntityToResponse(User entity) {
        // Phương thức này hiện tại trả về null, có thể chưa hoàn thiện.
        // Mục đích của phương thức này là chuyển từ `User` entity thành một kiểu trả về nào đó (P),
        // có thể là một DTO hoặc bất kỳ đối tượng nào khác.

        return null;
    }


}
