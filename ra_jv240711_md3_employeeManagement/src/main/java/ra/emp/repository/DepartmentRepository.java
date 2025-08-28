package ra.emp.repository;

import ra.emp.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department findById(int id);
    boolean create(Department department);
    boolean update(Department department);
    boolean delete(int id);
    List<Department> findAll();
//    List<Department> search(String searchTerm);
}

