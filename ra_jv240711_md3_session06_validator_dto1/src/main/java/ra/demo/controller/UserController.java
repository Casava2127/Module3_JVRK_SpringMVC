package ra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.demo.dto.request.LoginDtoRequest;
import ra.demo.dto.request.RegisterDtoRequest;
import ra.demo.dto.response.LoginDtoResponse;
import ra.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/initLogin")
    public String initLogin(Model model) {
        model.addAttribute("userLogin", new LoginDtoRequest());
        return "login";
    }

    @GetMapping("initRegister")
    public String initRegister(Model model) {
        model.addAttribute("userRegister", new RegisterDtoRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegister") RegisterDtoRequest userRegister, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        boolean result = userService.create(userRegister);
        if (result) {
            return "login";
        }
        return "error";
    }
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("userLogin") LoginDtoRequest userLogin, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        LoginDtoResponse userLoginResponse = userService.findUser(userLogin);
        if (userLoginResponse == null) {
            return "redirect:/userController/initLogin";
        }
        model.addAttribute("userLoginResponse", userLoginResponse);
        return "home";

    }
}
