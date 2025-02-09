package ra.demo.service;

import ra.demo.dto.request.EmployeeRequestDto;
import ra.demo.model.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    boolean save(EmployeeRequestDto employeeDto) throws IOException;
}
