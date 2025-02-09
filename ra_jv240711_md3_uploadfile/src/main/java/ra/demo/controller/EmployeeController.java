package ra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.demo.dto.request.EmployeeRequestDto;
import ra.demo.model.Employee;
import ra.demo.service.EmployeeService;
import ra.demo.service.UploadFileService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("employeeController")
public class EmployeeController {
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/initCreate")
    public String initCreate(Model model) {
        model.addAttribute("employee", new EmployeeRequestDto());
        return "newEmployee";
    }

    @PostMapping("/create")
    public String createEmployee(EmployeeRequestDto employee) throws IOException {
        boolean result = employeeService.save(employee);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Employee> listEmployees = employeeService.findAll();
        model.addAttribute("listEmployees", listEmployees);
        return "employees";
    }
}
