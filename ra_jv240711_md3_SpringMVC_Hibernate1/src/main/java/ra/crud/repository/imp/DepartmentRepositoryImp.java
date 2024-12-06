package ra.crud.repository.imp;
// Đặt tên package cho lớp `DepartmentRepositoryImp`, thường được sử dụng để chứa các lớp repository trong ứng dụng.

import org.springframework.stereotype.Repository;
// Import annotation `@Repository` từ Spring để đánh dấu lớp này là một Repository, Spring sẽ tự động nhận diện và quản lý như một bean trong Spring context.

import org.springframework.transaction.annotation.Transactional;
// Import annotation `@Transactional` từ Spring, giúp đảm bảo rằng các phương thức được đánh dấu này sẽ thực hiện trong một giao dịch, nghĩa là chúng sẽ được commit hoặc rollback trong một giao dịch.

import ra.crud.model.Department;
// Import lớp `Department` - đối tượng entity mà repository này sẽ thao tác với (lớp này ánh xạ với bảng `Department` trong cơ sở dữ liệu).

import ra.crud.repository.DepartmentRepository;
// Import interface `DepartmentRepository` mà lớp này phải thực thi. Interface này định nghĩa các phương thức mà `DepartmentRepositoryImp` phải cung cấp.

import javax.persistence.EntityManager;
// Import `EntityManager` từ JPA, đây là đối tượng chính để thực hiện các thao tác CRUD trên cơ sở dữ liệu.

import javax.persistence.PersistenceContext;
// Import annotation `@PersistenceContext`, giúp Spring tự động tiêm đối tượng `EntityManager` vào lớp này, giúp thao tác với cơ sở dữ liệu.

import java.util.List;
// Import lớp `List` từ Java để sử dụng kiểu dữ liệu danh sách cho kết quả trả về từ các phương thức.

@Repository
// Đánh dấu lớp này là một repository Spring, giúp Spring quản lý lớp như một bean trong container và cung cấp các phương thức thao tác với cơ sở dữ liệu.

public class DepartmentRepositoryImp implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    // Tiêm đối tượng `EntityManager` từ JPA vào lớp này, `EntityManager` dùng để thực hiện các thao tác CRUD với cơ sở dữ liệu.

    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("from Department", Department.class).getResultList();
        // Phương thức `findAll()` thực hiện truy vấn tất cả các bản ghi từ bảng `Department`.
        // `createQuery("from Department", Department.class)` tạo ra một câu truy vấn JPQL để lấy tất cả các đối tượng `Department`.
        // `getResultList()` trả về danh sách các đối tượng `Department` từ cơ sở dữ liệu.
    }

    @Override
    public Department findById(int deptId) {
        return entityManager.createQuery("from Department where deptId=:id", Department.class)
                .setParameter("id", deptId).getSingleResult();
        // Phương thức `findById(int deptId)` tìm kiếm một đối tượng `Department` theo `deptId` (khóa chính).
        // `createQuery("from Department where deptId=:id", Department.class)` tạo câu truy vấn JPQL để tìm kiếm theo `deptId`.
        // `setParameter("id", deptId)` thay thế tham số `:id` bằng giá trị `deptId`.
        // `getSingleResult()` trả về duy nhất một đối tượng `Department` tìm thấy.
    }

    @Override
    @Transactional
    public boolean save(Department department) {
        try {
            entityManager.persist(department);
            // `persist(department)` dùng để lưu đối tượng `Department` vào cơ sở dữ liệu (INSERT).
            // Đối tượng `department` sẽ được lưu vào bảng `Department`.
            return true; // Trả về `true` nếu thao tác lưu thành công.
        } catch (Exception ex) {
            ex.printStackTrace();
            // Nếu có lỗi xảy ra trong quá trình lưu, in ra thông báo lỗi.
        }
        return false; // Trả về `false` nếu có lỗi trong quá trình lưu đối tượng.
    }

    @Override
    @Transactional
    public boolean update(Department department) {
        try {
            entityManager.merge(department);
            // `merge(department)` dùng để cập nhật đối tượng `Department` vào cơ sở dữ liệu (UPDATE).
            // Nếu đối tượng chưa có trong cơ sở dữ liệu, `merge` sẽ thực hiện thao tác INSERT.
            return true; // Trả về `true` nếu thao tác cập nhật thành công.
        } catch (Exception ex) {
            ex.printStackTrace();
            // Nếu có lỗi xảy ra trong quá trình cập nhật, in ra thông báo lỗi.
        }
        return false; // Trả về `false` nếu có lỗi trong quá trình cập nhật.
    }

    @Override
    @Transactional
    public boolean delete(int deptId) {
        try {
            // 1. Lay doi tuong can xoa theo deptId
            Department department = findById(deptId);
            // Tìm đối tượng `Department` cần xóa bằng phương thức `findById(deptId)`.

            // 2. Thuc hien xoa
            entityManager.remove(department);
            // `remove(department)` dùng để xóa đối tượng `Department` khỏi cơ sở dữ liệu.
            return true; // Trả về `true` nếu thao tác xóa thành công.
        } catch (Exception ex) {
            ex.printStackTrace();
            // Nếu có lỗi xảy ra trong quá trình xóa, in ra thông báo lỗi.
        }
        return false; // Trả về `false` nếu có lỗi trong quá trình xóa đối tượng.
    }
}
