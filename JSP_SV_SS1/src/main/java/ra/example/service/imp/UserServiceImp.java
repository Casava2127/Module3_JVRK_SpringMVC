package ra.example.service.imp;

import jdk.jfr.internal.Repository;
import ra.example.entity.User;
import ra.example.reposistory.Imp.UserRepositoryImp;
import ra.example.reposistory.UserRepository;
import ra.example.service.UserService;

import java.util.List;

public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    //constructor
    public UserServiceImp() {
        userRepository = new UserRepositoryImp();
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId);
    }
}
