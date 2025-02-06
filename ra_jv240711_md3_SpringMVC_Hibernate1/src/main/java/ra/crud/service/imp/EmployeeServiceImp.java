package ra.crud.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.crud.model.Department;
import ra.crud.model.Employee;
import ra.crud.repository.EmployeeRepository;
import ra.crud.service.EmployeeService;

import java.util.List;
@Service

public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String emp_id) {
        return employeeRepository.findById(emp_id);
    }

    @Override
    public boolean save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean update(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public boolean delete(String emp_id) {
        return employeeRepository.delete(emp_id);
    }
}
