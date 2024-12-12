package ra.demo.controller;
// Khai báo package chứa lớp controller.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
// Import các annotation của Spring MVC để xử lý các request và response.

import ra.demo.dto.request.LoginDtoRequest;
import ra.demo.dto.request.RegisterDtoRequest;
import ra.demo.dto.response.LoginDtoResponse;
import ra.demo.service.UserService;
// Import các DTO và Service được sử dụng trong controller.

import javax.validation.Valid;
// Import annotation `@Valid` để kiểm tra tính hợp lệ của dữ liệu đầu vào.

@Controller
@RequestMapping("/userController")
// Đánh dấu lớp này là một controller trong Spring MVC, xử lý các request bắt đầu với `/userController`.

public class UserController {
    @Autowired
    private UserService userService;
    // Tự động tiêm `UserService` vào controller để sử dụng các chức năng liên quan đến user.

    @GetMapping("/initLogin")
    public String initLogin(Model model) {
        model.addAttribute("userLogin", new LoginDtoRequest());
        // Thêm một đối tượng `LoginDtoRequest` mới vào model với tên `userLogin`.

        return "login";
        // Trả về tên của view `login.jsp` để hiển thị trang đăng nhập.
    }

    @GetMapping("initRegister")
    public String initRegister(Model model) {
        model.addAttribute("userRegister", new RegisterDtoRequest());
        // Thêm một đối tượng `RegisterDtoRequest` mới vào model với tên `userRegister`.

        return "register";
        // Trả về tên của view `register.jsp` để hiển thị trang đăng ký.
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("userRegister") RegisterDtoRequest userRegister,
            BindingResult bindingResult) {
        // Xử lý request POST đến `/userController/register`.
        // Dữ liệu từ form được gán vào `RegisterDtoRequest` và kiểm tra hợp lệ với `@Valid`.
        // `BindingResult` chứa thông tin về lỗi (nếu có).

        if (bindingResult.hasErrors()) {
            return "register";
            // Nếu có lỗi dữ liệu, trả về lại trang `register.jsp`.
        }

        boolean result = userService.create(userRegister);
        // Gọi service để tạo user mới dựa trên thông tin đăng ký.

        if (result) {
            return "login";
            // Nếu tạo thành công, chuyển hướng đến trang `login.jsp`.
        }

        return "error";
        // Nếu xảy ra lỗi, chuyển hướng đến trang `error.jsp`.
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("userLogin") LoginDtoRequest userLogin,
            BindingResult bindingResult,
            Model model) {
        // Xử lý request POST đến `/userController/login`.
        // Dữ liệu từ form được gán vào `LoginDtoRequest` và kiểm tra hợp lệ với `@Valid`.
        // `BindingResult` chứa thông tin về lỗi (nếu có).
        // `Model` dùng để thêm dữ liệu cho view.

        if (bindingResult.hasErrors()) {
            return "login";
            // Nếu có lỗi dữ liệu, trả về lại trang `login.jsp`.
        }

        LoginDtoResponse userLoginResponse = userService.findUser(userLogin);
        // Gọi service để tìm thông tin user dựa trên thông tin đăng nhập.

        if (userLoginResponse == null) {
            return "redirect:/userController/initLogin";
            // Nếu không tìm thấy user, chuyển hướng lại trang đăng nhập.
        }

        model.addAttribute("userLoginResponse", userLoginResponse);
        // Nếu tìm thấy user, thêm thông tin user vào model với tên `userLoginResponse`.

        return "home";
        // Trả về tên của view `home.jsp` để hiển thị trang chủ.
    }
}
