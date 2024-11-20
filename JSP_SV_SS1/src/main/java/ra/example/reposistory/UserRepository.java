package ra.example.reposistory;

import ra.example.entity.User;

import java.util.List;

public interface UserRepository {
    // Define methods for CRUD operations on user data
    List<User> findAll();
    boolean save(User user);
    User findById(int userId);

}
