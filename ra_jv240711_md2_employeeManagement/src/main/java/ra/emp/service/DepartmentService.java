package ra.emp.service;

import ra.emp.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllActive();
}
