package ra.emp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.emp.model.Department;
import ra.emp.repository.DepartmentRepository;
import ra.emp.repository.imp.DepartmentRepositoryImp;
import ra.emp.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImp(DepartmentRepositoryImp departmentRepositoryImp) {
        this.departmentRepository = departmentRepositoryImp;
    }

    @Override
    public List<Department> findAllActive() {
        return departmentRepository.findAll();
    }



    @Override
    public boolean updateDepartment(Department department) {
        return departmentRepository.update(department);
    }

    @Override
    public Department findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public boolean create(Department department) {
        return departmentRepository.create(department);
    }
    @Override
    public boolean delete(int id) {
        return departmentRepository.delete(id);
    }



}
