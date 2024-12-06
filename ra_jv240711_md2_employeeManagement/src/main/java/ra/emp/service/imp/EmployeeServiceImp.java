package ra.emp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.emp.model.Employee;
import ra.emp.repository.EmployeeRepository;
import ra.emp.repository.imp.EmployeeRepositoryImp;
import ra.emp.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepositoryImp employeeRepositoryImp) {
        this.employeeRepository = employeeRepositoryImp;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean create(Employee emp) {
        return employeeRepository.create(emp);
    }

    @Override
    public Employee findById(String id){
        return employeeRepository.findById(id);
    }
    @Override
    public boolean updateEmp(Employee emp) {
        return employeeRepository.updateEmp(emp);
    }

    @Override
    public boolean deleteEmp(String id) {
        return employeeRepository.delete(id);
    }


}
