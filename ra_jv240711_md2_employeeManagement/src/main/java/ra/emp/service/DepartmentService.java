package ra.emp.service;

import ra.emp.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllActive();
//    Department create(String department);
    boolean updateDepartment(Department department);
    Department findById(int id);
    boolean create(Department department);

    boolean delete(int id);
}
