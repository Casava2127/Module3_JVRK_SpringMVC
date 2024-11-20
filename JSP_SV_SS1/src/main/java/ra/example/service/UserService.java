package ra.example.service;

import ra.example.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    boolean save(User user);
    User findById(int userId);
}
