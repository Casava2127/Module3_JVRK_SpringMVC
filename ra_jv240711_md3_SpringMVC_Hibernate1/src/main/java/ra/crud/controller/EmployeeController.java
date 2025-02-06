package ra.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.crud.model.Department;
import ra.crud.model.Employee;
import ra.crud.service.DepartmentService;
import ra.crud.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employeeController")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * Lấy danh sách tất cả nhân viên và hiển thị trên trang danh sách nhân viên.
     */
    @GetMapping("/findAll")
    public String findAllEmployees(Model model) {
        // Gọi service để lấy danh sách nhân viên
        List<Employee> ListEmployees = employeeService.findAll();
        model.addAttribute("ListEmployees", ListEmployees);
        return "employees"; // Tên file JSP hiển thị danh sách nhân viên
    }

    /**
     * Khởi tạo form cập nhật thông tin nhân viên.
     *
     * @param model Model để truyền dữ liệu
     * @param empId ID của nhân viên cần cập nhật
     * @return Tên file JSP cho form cập nhật nhân viên
     */
    @GetMapping("/initUpdate")
    public String initUpdateEmployee(Model model, @RequestParam("empId") String empId) {
        Employee empUpdate = employeeService.findById(empId);
        if (empUpdate == null) {
            throw new RuntimeException("Employee not found with ID: " + empId);
        }
        List<Department> listDepartments = departmentService.findAll();
        if (listDepartments == null || listDepartments.isEmpty()) {
            throw new RuntimeException("No departments available.");
        }
        model.addAttribute("empUpdate", empUpdate);
        model.addAttribute("listDepartments", listDepartments);
        return "updateEmployee";
    }


    /**
     * Cập nhật thông tin nhân viên sau khi người dùng gửi form.
     *
     * @param empUpdate Thông tin nhân viên cần cập nhật
     * @return Tên view điều hướng sau khi cập nhật
     */
    @PostMapping("/update")
    public String updateEmployee(Employee empUpdate, @RequestParam("status") String status) {
        if ("Active".equals(status)) {
            empUpdate.setStatus(true);
        } else if ("Inactive".equals(status)) {
            empUpdate.setStatus(false);
        }

        boolean result = employeeService.update(empUpdate);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }

}
