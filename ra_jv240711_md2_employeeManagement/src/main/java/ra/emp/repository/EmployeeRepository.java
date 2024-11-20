package ra.emp.repository;

import ra.emp.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();

    boolean create(Employee emp);
}
