package ra.demo.repository.imp;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.demo.model.User;
import ra.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImp implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUser(User user) {
        try {
            return entityManager.createQuery("from User where userName=:name and password=:pass and status=true", User.class)
                    .setParameter("name", user.getUserName())
                    .setParameter("pass", user.getPassword()).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    @Transactional
    public boolean create(User user) {
        try {
            entityManager.persist(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
