package ra.emp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.emp.model.Department;
import ra.emp.model.Employee;
import ra.emp.service.DepartmentService;
import ra.emp.service.EmployeeService;
import ra.emp.service.imp.DepartmentServiceImp;
import ra.emp.service.imp.EmployeeServiceImp;

import java.util.List;

@Controller
@RequestMapping("/employeeController")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeServiceImp employeeServiceImp, DepartmentServiceImp departmentServiceImp) {
        this.employeeService = employeeServiceImp;
        this.departmentService = departmentServiceImp;
    }

    @GetMapping("/findAll")
    public String findAllEmployees(Model model) {
        List<Employee> listEmployees = employeeService.findAll();
        model.addAttribute("listEmployees", listEmployees);
        return "employees";
    }

    @GetMapping("/initCreate")
    public String initCreateEmployee(Model model) {
        Employee employee = new Employee();
        List<Department> listDepartments = departmentService.findAllActive();
        model.addAttribute("employee", employee);
        model.addAttribute("listDepartments", listDepartments);
        return "newEmployee";
    }
    @PostMapping("/create")
    public String createEmployee(Employee employee) {
        boolean result = employeeService.create(employee);
        if (result){
            return "redirect:findAll";
        }
        return "error";
    }
}
