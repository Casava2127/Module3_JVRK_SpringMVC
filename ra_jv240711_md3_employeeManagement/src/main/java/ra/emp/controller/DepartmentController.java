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
@RequestMapping("/departmentController")
public class DepartmentController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public DepartmentController(EmployeeServiceImp employeeServiceImp, DepartmentServiceImp departmentServiceImp) {
        this.employeeService = employeeServiceImp;
        this.departmentService = departmentServiceImp;
    }


    @GetMapping("/findAll")
    public String findAllDepartments(Model model) {
        List<Department> listDepartments = departmentService.findAllActive();
        model.addAttribute("listDepartments", listDepartments);
        return "departments";
    }
    @GetMapping("/initCreate")
    public String initCreateDepartment(Model model) {
        Department department = new Department();
        List<Employee> listEmployees = employeeService.findAll();
        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("department", department);
        return "newDepartment";
    }
    @PostMapping("/create")
    public String createDepartment(Department department) {
        boolean result = departmentService.create(department);
        if (result) {
            return "redirect:findAll";
        }else {
            return "error";
        }

    }
    @GetMapping("/initUpdate")
    public String initUpdateDepartment(Model model, int id) {
        Department department = departmentService.findById(id);
        List<Employee> listEmployees = employeeService.findAll();
        model.addAttribute("department", department);
        model.addAttribute("listEmployees", listEmployees);
        return "updateDepartment";
    }
    @PostMapping("/update")
    public String updateDepartment(Department department) {
        boolean result = departmentService.updateDepartment(department);
        if(result) {
            return "redirect:findAll";
        }else {
            return "error";
        }
    }
}
