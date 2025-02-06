package ra.crud.repository;

import ra.crud.model.Department;
import ra.crud.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> findAll();

    Employee findById(String empId);

    boolean save(Employee employee); ;

    boolean update(Employee employee);

    boolean delete(String  empId);
}
