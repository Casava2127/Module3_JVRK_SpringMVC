package ra.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.demo.dto.request.EmployeeRequestDto;
import ra.demo.model.Employee;
import ra.demo.repository.EmployeeRepository;
import ra.demo.service.EmployeeService;
import ra.demo.service.UploadFileService;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean save(EmployeeRequestDto employeeDto) throws IOException {
        String avatarLink = uploadFileService.uploadFile(employeeDto.getAvatar());
        Employee employee = new Employee(employeeDto.getEmpId(), employeeDto.getEmpName(), avatarLink);
        return employeeRepository.save(employee);
    }
}
