package ra.demo.repository;

import ra.demo.model.User;

public interface UserRepository {
    User findUser(User user);

    boolean create(User user);
}
