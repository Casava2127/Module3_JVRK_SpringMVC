package ra.crud.service.imp;
// Đặt tên package cho lớp `DepartmentServiceImp`, nơi chứa các lớp service thực thi các phương thức nghiệp vụ.

import org.springframework.beans.factory.annotation.Autowired;
// Import annotation `@Autowired` từ Spring để tự động tiêm các bean vào trong lớp này.

import org.springframework.stereotype.Service;
// Import annotation `@Service` từ Spring để đánh dấu lớp này là một service, giúp Spring quản lý lớp như một bean trong Spring context.

import ra.crud.model.Department;
// Import lớp `Department`, đối tượng entity mà service này thao tác với.

import ra.crud.repository.DepartmentRepository;
// Import interface `DepartmentRepository`, interface này cung cấp các phương thức để tương tác với cơ sở dữ liệu.

import ra.crud.service.DepartmentService;
// Import interface `DepartmentService`, interface này định nghĩa các phương thức dịch vụ cho đối tượng `Department`.

import java.util.List;
// Import lớp `List` để sử dụng kiểu dữ liệu danh sách cho kết quả trả về từ các phương thức.

@Service
// Đánh dấu lớp này là một service trong Spring, Spring sẽ tự động nhận diện và quản lý lớp này như một bean trong container.

public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    // Tiêm đối tượng `DepartmentRepository` vào trong lớp này. `DepartmentRepository` sẽ cung cấp các phương thức để thao tác với cơ sở dữ liệu.

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
        // Phương thức `findAll()` gọi phương thức `findAll()` của `departmentRepository` để lấy tất cả các đối tượng `Department` từ cơ sở dữ liệu.
        // Kết quả trả về là một danh sách các `Department`.
    }

    @Override
    public Department findById(int deptId) {
        return departmentRepository.findById(deptId);
        // Phương thức `findById(int deptId)` gọi phương thức `findById(deptId)` của `departmentRepository` để tìm kiếm một đối tượng `Department` theo `deptId`.
        // Trả về một đối tượng `Department` tìm thấy theo `deptId`.
    }

    @Override
    public boolean save(Department department) {
        return departmentRepository.save(department);
        // Phương thức `save(Department department)` gọi phương thức `save(department)` của `departmentRepository` để lưu đối tượng `Department` vào cơ sở dữ liệu.
        // Trả về giá trị `true` nếu lưu thành công, `false` nếu thất bại.
    }

    @Override
    public boolean update(Department department) {
        return departmentRepository.update(department);
        // Phương thức `update(Department department)` gọi phương thức `update(department)` của `departmentRepository` để cập nhật thông tin của đối tượng `Department` trong cơ sở dữ liệu.
        // Trả về `true` nếu cập nhật thành công, `false` nếu thất bại.
    }

    @Override
    public boolean delete(int deptId) {
        return departmentRepository.delete(deptId);
        // Phương thức `delete(int deptId)` gọi phương thức `delete(deptId)` của `departmentRepository` để xóa một đối tượng `Department` theo `deptId`.
        // Trả về `true` nếu xóa thành công, `false` nếu thất bại.
    }
}
