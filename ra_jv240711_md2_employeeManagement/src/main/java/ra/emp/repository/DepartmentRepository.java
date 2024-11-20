package ra.emp.repository;

import ra.emp.model.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAllActive();
}
