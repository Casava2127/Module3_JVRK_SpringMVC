package ra.demo.repository.imp;

import ra.demo.model.User;
import ra.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class UserRepositoryImp implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUser(User user) {
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
    }

    @Override
    @Transactional
    public boolean create(User user) {
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
