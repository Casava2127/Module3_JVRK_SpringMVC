package ra.crud.repository;

import ra.crud.model.Department;
import ra.crud.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();

    Department findById(int empId);

    boolean save(Employee employee); ;

    boolean update(Employee employee);

    boolean delete(int empId);
}
