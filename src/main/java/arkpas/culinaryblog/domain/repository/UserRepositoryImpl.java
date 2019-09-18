package arkpas.culinaryblog.domain.repository;

import arkpas.culinaryblog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User getUser(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public User getUser(String username) {
        List<User> results = entityManager.createQuery("SELECT u FROM User AS u WHERE username = :username", User.class).setParameter("username", username).getResultList();
        User user = null;
        if (!results.isEmpty())
            user = results.get(0);
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);

    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        entityManager.remove(user);
    }
}
