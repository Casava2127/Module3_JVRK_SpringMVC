package ra.emp.service;

import ra.emp.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    boolean create(Employee emp);

    Employee findById(String id);

    boolean updateEmp(Employee emp);
    boolean deleteEmp(String id);
}
