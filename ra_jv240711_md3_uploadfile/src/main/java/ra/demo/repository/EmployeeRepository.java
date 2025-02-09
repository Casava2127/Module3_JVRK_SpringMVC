package ra.demo.repository;

import ra.demo.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();

    boolean save(Employee employee);

}
