package ra.demo.service.imp;
// Khai báo package chứa lớp `UserServiceImp`.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demo.dto.request.LoginDtoRequest;
import ra.demo.dto.request.RegisterDtoRequest;
import ra.demo.dto.response.LoginDtoResponse;
import ra.demo.mapper.LoginMapper;
import ra.demo.mapper.RegisterMapper;
import ra.demo.model.User;
import ra.demo.repository.UserRepository;
import ra.demo.service.UserService;
// Import các thành phần liên quan như DTO, mapper, model, repository và service.

@Service
public class UserServiceImp implements UserService {
    // Đánh dấu lớp này là một service trong Spring, giúp quản lý logic nghiệp vụ liên quan đến user.

    @Autowired
    private UserRepository userRepository;
    // Tự động tiêm `UserRepository` để thao tác với dữ liệu trong cơ sở dữ liệu.

    @Autowired
    private LoginMapper loginMapper;
    // Tự động tiêm `LoginMapper` để chuyển đổi giữa DTO và entity cho login.

    @Autowired
    private RegisterMapper registerMapper;
    // Tự động tiêm `RegisterMapper` để chuyển đổi giữa DTO và entity cho register.

    @Override
    public LoginDtoResponse findUser(LoginDtoRequest loginDtoRequest) {
        // Phương thức tìm user dựa trên thông tin đăng nhập.

        // 1. request --> entity
        User user = loginMapper.mapperRequestToEntity(loginDtoRequest);
        // Chuyển đổi từ đối tượng DTO `LoginDtoRequest` sang entity `User` bằng `LoginMapper`.

        // 2. Goi repository --> entity
        User userLogin = userRepository.findUser(user);
        // Gọi repository để tìm kiếm user trong cơ sở dữ liệu dựa trên thông tin trong entity `User`.

        // 3. entity --> response
        return loginMapper.mapperEntityToResponse(userLogin);
        // Chuyển đổi từ entity `User` sang DTO `LoginDtoResponse` bằng `LoginMapper` và trả về kết quả.
    }

    @Override
    public boolean create(RegisterDtoRequest registerDtoRequest) {
        // Phương thức tạo mới một user dựa trên thông tin đăng ký.

        // 1. request --> entity
        User user = registerMapper.mapperRequestToEntity(registerDtoRequest);
        // Chuyển đổi từ đối tượng DTO `RegisterDtoRequest` sang entity `User` bằng `RegisterMapper`.

        // 2. goi repository --> boolean
        return userRepository.create(user);
        // Gọi repository để lưu user vào cơ sở dữ liệu và trả về kết quả (true/false).
    }
}
