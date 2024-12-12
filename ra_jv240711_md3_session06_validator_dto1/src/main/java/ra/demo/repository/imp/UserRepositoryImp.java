package ra.demo.repository.imp;
// Khai báo package chứa lớp `UserRepositoryImp`.

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.demo.model.User;
import ra.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
// Import các thư viện liên quan đến JPA và các annotation Spring.

@Repository
public class UserRepositoryImp implements UserRepository {
    // Đánh dấu lớp này là một repository trong Spring, phục vụ việc truy cập và xử lý dữ liệu trong database.
    // Lớp này thực hiện giao diện `UserRepository`.

    @PersistenceContext
    private EntityManager entityManager;
    // `@PersistenceContext` giúp quản lý thực thể `EntityManager` để thao tác với cơ sở dữ liệu.

    @Override
    public User findUser(User user) {
        // Phương thức tìm kiếm user trong cơ sở dữ liệu dựa trên tên đăng nhập, mật khẩu và trạng thái.
        try {
            return entityManager.createQuery(
                            "from User where userName=:name and password=:pass and status=true", User.class)
                    .setParameter("name", user.getUserName())
                    .setParameter("pass", user.getPassword())
                    .getSingleResult();
            // Sử dụng JPQL (Java Persistence Query Language) để tìm user:
            // - Tìm user với `userName`, `password` khớp và `status` phải bằng `true`.
            // - `setParameter` được sử dụng để tránh SQL injection và truyền giá trị động vào truy vấn.
        } catch (Exception ex) {
            ex.printStackTrace();
            // Nếu xảy ra lỗi (như không tìm thấy user), in lỗi ra console.
        }

        return null;
        // Trả về `null` nếu không tìm thấy user hoặc xảy ra lỗi.
    }

    @Override
    @Transactional
    public boolean create(User user) {
        // Phương thức thêm mới một user vào cơ sở dữ liệu.
        // `@Transactional` đảm bảo rằng thao tác thêm sẽ được thực hiện trong một giao dịch.
        try {
            entityManager.persist(user);
            // Sử dụng `persist` để lưu đối tượng `user` vào cơ sở dữ liệu.

            return true;
            // Trả về `true` nếu thêm thành công.
        } catch (Exception ex) {
            ex.printStackTrace();
            // Nếu xảy ra lỗi, in lỗi ra console.
        }
        return false;
        // Trả về `false` nếu thêm thất bại.
    }
}
