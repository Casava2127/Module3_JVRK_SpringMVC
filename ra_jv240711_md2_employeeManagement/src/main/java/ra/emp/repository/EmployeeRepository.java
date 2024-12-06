package ra.emp.repository;

import ra.emp.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();

    boolean create(Employee emp);

    Employee findById(String id);

    boolean updateEmp(Employee emp);

    boolean delete(String Id);
}
